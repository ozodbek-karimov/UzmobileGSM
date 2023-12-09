package pl.ozodbek.uzmobilegsm


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import pl.ozodbek.uzmobilegsm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragmentMain) as NavHostFragment
        navController = navHostFragment.navController

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.balansFragment,
                R.id.operatorFragment,
                R.id.yangiliklarFragment,
                R.id.sozlamalarFragment
            )
        )

        binding.bottomnav.setupWithNavController(navController)
        binding.bottomnav.selectedItemId = R.id.action_add
//        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.floatingActionButton.setOnClickListener {
            navController.navigate(R.id.homeFragment)
            binding.bottomnav.selectedItemId = R.id.action_add
        }



        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.splashScreen) {
                binding.appbar.visibility = View.INVISIBLE
                binding.floatingActionButton.visibility = View.INVISIBLE
            } else {
                    binding.appbar.visibility = View.VISIBLE
                    binding.floatingActionButton.visibility = View.VISIBLE
            }
        }

    }

    override fun onBackPressed() {
        when{
            navController.currentDestination?.id != R.id.homeFragment ->{
                navController.navigate(R.id.homeFragment)
                binding.bottomnav.selectedItemId = R.id.action_add
            }
            navController.currentDestination?.id == R.id.homeFragment ->{
                // Show confirmation dialog before exiting the app
                AlertDialog.Builder(this)
                    .setTitle("Exit App")
                    .setMessage("Are you sure you want to exit the app?")
                    .setPositiveButton("Yes") { _, _ ->
                        finish()
                    }
                    .setNegativeButton("No", null)
                    .show()
            }
            else -> super.onBackPressed()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
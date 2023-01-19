package hr.algebra.navigationandtoolbar

import android.app.DatePickerDialog
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate( savedInstanceState: Bundle? ) {
        super.onCreate( savedInstanceState )
        setContentView( R.layout.activity_main )

        setSupportActionBar( toolbar )
        supportActionBar?.setDisplayHomeAsUpEnabled( true )

    }

    override fun onCreateOptionsMenu( menu: Menu? ): Boolean {
        menuInflater.inflate( R.menu.menu, menu )
        return super.onCreateOptionsMenu( menu )
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when( item.itemId ) {
            R.id.aTost   -> Toast.makeText( this, "Pozdrav iz Toast-a", Toast.LENGTH_SHORT ).show( )
            R.id.aDialog -> displayDialog( )
            R.id.aDijalogDatum -> displayDateDialog( )
            R.id.aSnack -> Snackbar
                                .make( tvDatum, "Ipak ne želite crvenu aplikaciju?", Snackbar.LENGTH_INDEFINITE )
                                .setTextColor( Color.RED )
                                .setActionTextColor( Color.YELLOW )
                                .setAction( "Vrati bijelo!", View.OnClickListener {
                                    root.setBackgroundColor( Color.WHITE )
                                } )
                                .show( )
        }
        return true
    }

    private fun displayDialog( ) {
        val builder = AlertDialog.Builder( this ).apply {
            title = "Set background color"
            setMessage( "Do You want to change app background color to red?" )
            setPositiveButton( "YES " ) { x, y ->
                root.setBackgroundColor( Color.RED )
            }
            setNegativeButton( "NO" ) { x, y -> }
            setNeutralButton( "Cancel " ) { x, y ->
                Toast
                    .makeText( this@MainActivity, "Dialog canceled...", Toast.LENGTH_SHORT )
                    .show( )
            }
        }
        builder.create( ).show( )
    }

    private fun displayDateDialog( ) {
        val c     = Calendar.getInstance( )
        val year  = c.get( Calendar.YEAR )
        val month = c.get( Calendar.MONTH )
        val day   = c.get( Calendar.DAY_OF_MONTH )


        val dpd = DatePickerDialog( this, DatePickerDialog.OnDateSetListener { view, g, m, d ->
            tvDatum.text = "Odabrani datum je: $d. ${m+1}. $g."
        }, year, month, day)

        dpd.show( )
    }

}
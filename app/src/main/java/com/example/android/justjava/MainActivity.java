/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity=1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        EditText name = findViewById(R.id.name_id);
        //Editable nameField = name.getText();
        String nameField = name.getText().toString();
        //Log.v("MainActivity.java",""+nameField);
        CheckBox whippedCream = findViewById(R.id.whipped_cream_check_box);
        CheckBox chocolate = findViewById(R.id.chocolate_check_box);
        boolean hasChocolate = chocolate.isChecked();
        boolean hasWhippedCream = whippedCream.isChecked();
        int price = calculatePrice(hasWhippedCream, hasChocolate);
        Log.v("MainActivity","Total prie:"+hasWhippedCream);
        String priceMessage = getString(R.string.namejava)+ nameField + "\n" + getString(R.string.cream) +hasWhippedCream+ "\n" + getString(R.string.choco) + hasChocolate + "\n" + getString(R.string.quan) + +quantity + "\n" + getString(R.string.total) + price + "\n" + getString(R.string.thank_you);
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just java order for"+nameField);
        intent.putExtra(Intent.EXTRA_TEXT, "Just java order for"+priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }


    public void increment(View view) {
        if(quantity==100) {
            Toast.makeText(this,"You cannot have more than 100 cups of coffee",Toast.LENGTH_SHORT).show();
            return;
        }
        quantity=quantity+1;
        displayQuantity(quantity);
    }

    public void   decrement(View view) {
        if(quantity==1) {
            Toast.makeText(this,"You cannot have less than 1 cup of coffee",Toast.LENGTH_SHORT).show();
            return;
        }
        quantity=quantity-1;
        displayQuantity(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText(" "+number);
    }
    /**
     * This method displays the given price on the screen.
     */

    /**
     * Calculates the price of the order.
     *
     * @return total_price
     */
    private int  calculatePrice(boolean hasWhippedCream,boolean hasChocolate) {

        int  basePrice = 5;
        if(hasWhippedCream) {
            basePrice+=1;
        }
        if(hasChocolate) {
            basePrice+=2;
        }
        return quantity * basePrice;
    }

    /**
     * This method displays the given text on the screen.
     */

}
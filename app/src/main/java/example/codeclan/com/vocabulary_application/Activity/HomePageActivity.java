package example.codeclan.com.vocabulary_application.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import example.codeclan.com.vocabulary_application.Database.DatabaseRunner;
import example.codeclan.com.vocabulary_application.R;

public class HomePageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        DatabaseRunner.fuelDatabase(this);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_vocab:
                startActivity(new Intent(this, WordsListActivity.class));
                return true;
            case R.id.nav_training:
                startActivity(new Intent(this, TrainingListActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}

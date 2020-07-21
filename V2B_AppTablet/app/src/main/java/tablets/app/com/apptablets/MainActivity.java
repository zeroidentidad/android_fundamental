package tablets.app.com.apptablets;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements ListaFragment.CallBacks {
    public static boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(findViewById(R.id.list_item) != null){
            mTwoPane = true;
            if(savedInstanceState == null){
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.list_item,new ItemsFragment(),"ELEMENT").commit();
            }else{
                mTwoPane = false;
            }
        }

        }

    public void onItemSelected(String nombrelista,String lista){
        if(mTwoPane){
           Bundle bundle  = new Bundle();
            bundle.putString("nombrelista", nombrelista);
            bundle.putString("lista",lista);
            ItemsFragment itemsFragment = new ItemsFragment();
            itemsFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().
                    replace(R.id.list_item,itemsFragment).commit();
        }else{
            Intent intent = new Intent( MainActivity.this,ItemList.class);
            intent.putExtra("nombrelista",nombrelista);
            intent.putExtra("lista",lista);
            startActivity(intent);
        }


    }


}

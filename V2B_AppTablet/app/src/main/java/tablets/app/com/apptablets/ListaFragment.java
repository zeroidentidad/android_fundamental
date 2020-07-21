package tablets.app.com.apptablets;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

/**
 * Created by booth05-mgr2 on 18/01/2017.
 */

public class ListaFragment  extends Fragment {
    RecyclerView recyclerView;
    DataBaseHelper myDBHelper;
    ListaAdapter adapter;

    public interface CallBacks{
        public void onItemSelected(String nombrelista, String lista);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView  = inflater.inflate(R.layout.lista_fragment,container,false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.lista);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));

        myDBHelper = new DataBaseHelper(getActivity().getApplicationContext());

        try {
            myDBHelper.createDataBase();
        } catch (IOException e) {
            throw new Error("No se puede crear la BD");
        }

        myDBHelper.openDatabase();
        Cursor cursor = myDBHelper.fetchAlllist();
        if(cursor != null){
            adapter = new ListaAdapter(getActivity().getApplicationContext(),cursor);
            recyclerView.setAdapter(adapter);
            recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity().getApplicationContext(),new OnItemClickListener()));
        }

        return rootView;



    }

    private class OnItemClickListener extends  RecyclerItemClickListener.SimpleOnItemClickListener{
        @Override
        public void onItemClick(View chidView, int position){
            TextView textView = (TextView) chidView.findViewById(R.id.title);
            TextView listanombre = (TextView) chidView.findViewById(R.id.listanombre);
           // Toast.makeText(getActivity().getApplicationContext(),"Hola!",Toast.LENGTH_SHORT).show();
            ((CallBacks)getActivity()).onItemSelected(textView.getText().toString(),listanombre.getText().toString());
           }

    }


}

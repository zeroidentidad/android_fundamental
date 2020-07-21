package tablets.app.com.apptablets;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by booth05-mgr2 on 18/01/2017.
 */

public class ItemsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.items_fragment,container,false);
        TextView textView = (TextView) rootView.findViewById(R.id.texto);
        Bundle bundle = getArguments();

        if(bundle != null){
            textView.setText(bundle.getString("nombrelista"));
        }


        return rootView;
    }
}

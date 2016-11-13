package cs222.bsu.edu.pigeonchat;

import android.content.Context;
import android.widget.Toast;

public class Toaster {
    private Context context;
    public Toaster(Context context){
        this.context = context;
    }
    public void popUp(String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

}

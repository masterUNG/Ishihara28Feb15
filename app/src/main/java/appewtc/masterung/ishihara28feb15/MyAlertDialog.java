package appewtc.masterung.ishihara28feb15;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by masterUNG on 3/1/15 AD.
 */
public class MyAlertDialog {

    public void noAnswer(Context context) {

        AlertDialog.Builder objBuilder = new AlertDialog.Builder(context);
        objBuilder.setIcon(R.drawable.icon_question);
        objBuilder.setTitle("กรุณาตอบคำถาม");
        objBuilder.setMessage("กรุณาตอบคำถาม ด้วยคะ");
        objBuilder.setCancelable(false);
        objBuilder.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        objBuilder.show();

    }   // noAnswer

}   // Main Class

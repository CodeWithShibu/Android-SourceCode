package dev.shibu.drivedirectdonloadlinkgenerator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.Arrays;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    Button paste,clear,generate,copy,reset;
    EditText driveLink,directLink;

    String defLink="https://drive.google.com/uc?export=download&id=";
    String finalLink;
    String DocID;



    ClipboardManager clipboardManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        paste=findViewById(R.id.paste);
        driveLink=findViewById(R.id.driveLink);
        clear=findViewById(R.id.clear);
        generate=findViewById(R.id.generate);
        directLink=findViewById(R.id.directlink);
        copy=findViewById(R.id.copy);
        reset=findViewById(R.id.reset);



        clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);

       paste.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               ClipData clipData=clipboardManager.getPrimaryClip();
               assert clipData != null;
               ClipData.Item item=clipData.getItemAt(0);
               driveLink.setText(item.getText().toString().trim());
           }
       });

       clear.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               driveLink.setText("");
           }
       });

       generate.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               String link=driveLink.getText().toString();
               
                   int DocIDstart = link.indexOf("/d/");
                   int DocIDend = link.indexOf('/', DocIDstart + 3);
                   if (DocIDstart == -1) {
                       Toast.makeText(getApplicationContext(), "Invalid url", Toast.LENGTH_SHORT).show();

                   }else if (DocIDend == -1 ) {
                       DocID = link.substring(DocIDstart + 3);
                       finalLink = defLink.concat((DocID));
                       directLink.setText(finalLink);

                   } else {
                       DocID = link.substring(DocIDstart + 3, DocIDend);
                       finalLink = defLink.concat((DocID));
                       directLink.setText(finalLink);


                   }




               }

       });

       copy.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

String text=directLink.getText().toString();
if (!text.equals("")){
    ClipData clipData=ClipData.newPlainText("text",text);
    clipboardManager.setPrimaryClip(clipData);
    Toast.makeText(getApplicationContext(),"Link Copied",Toast.LENGTH_SHORT).show();
    paste.setEnabled(true);
}

           }
       });

       reset.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               driveLink.setText("");
               directLink.setText("");
           }
       });


    }
}
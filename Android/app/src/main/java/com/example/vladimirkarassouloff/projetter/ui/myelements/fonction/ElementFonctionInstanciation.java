package com.example.vladimirkarassouloff.projetter.ui.myelements.fonction;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.vladimirkarassouloff.projetter.utils.Debug;
import com.example.vladimirkarassouloff.projetter.R;
import com.example.vladimirkarassouloff.projetter.customlistener.ValidationDialogFunction;
import com.example.vladimirkarassouloff.projetter.ui.myelements.DraggableElement;
import com.example.vladimirkarassouloff.projetter.ui.myelementsproduction.Production;
import com.example.vladimirkarassouloff.projetter.ui.myelementsproduction.ProductionBraceCloser;
import com.example.vladimirkarassouloff.projetter.ui.myelementsproduction.fonction.ProductionFonctionInstanciation;
import com.example.vladimirkarassouloff.projetter.myelementsstring.ElementString;
import com.example.vladimirkarassouloff.projetter.myelementsstring.fonction.FonctionInstanciationString;
import com.example.vladimirkarassouloff.projetter.ui.myviews.prompt.PromptTypeVariableView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vladimir on 16/02/2016.
 */
public class ElementFonctionInstanciation extends TextView implements DraggableElement {


    private FonctionInstanciationString element;

    private ProductionFonctionInstanciation tv;
    private ProductionBraceCloser bc;

    public ElementFonctionInstanciation(Context context){
        super(context);
        this.setText("Nouvelle Fonction");
    }
    public ElementFonctionInstanciation(Context context, AttributeSet attrs){
        super(context, attrs);
    }

    public String getType(){
        return element.getType();
    }



    @Override
    public List<View> onDraggedOnLine(View v) {
        List<View> array = new ArrayList<View>();
        tv = new ProductionFonctionInstanciation(v.getContext());
        bc = new ProductionBraceCloser(v.getContext());

        array.add(tv);
        array.add(bc);



        LayoutInflater li = LayoutInflater.from(v.getContext());
        View promptsView = li.inflate(R.layout.promptfunction, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(v.getContext());
        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);
        final EditText userInput = (EditText) promptsView.findViewById(R.id.editTextFunctionInput);
        final PromptTypeVariableView ptv = (PromptTypeVariableView) promptsView.findViewById(R.id.promptviewtypefunction);
        // set dialog message

        if(Debug.DEBUG_APP){
            userInput.setText("funcName");
        }

        alertDialogBuilder.setCancelable(false).setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        /*
                        name = userInput.getText().toString();
                        type = ptv.getType();

                        tv.setName(name);
                        tv.setType(type);

                        refreshTextView();

                        Intent intent = new Intent("newVariable");
                        // You can also include some extra data.
                        intent.putExtra("variable", userInput.getText().toString());
                        LocalBroadcastManager.getInstance(getContext()).sendBroadcast(intent);*/

                    }
                })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                ViewGroup owner = (ViewGroup) tv.getParent();
                                owner.removeView(tv);
                                owner.removeView(bc);
                                dialog.cancel();
                            }
                        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

        Button theButton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
        theButton.setOnClickListener(new ValidationDialogFunction(alertDialog,promptsView,tv));

        return array;
    }

    @Override
    public ElementString onDraggedOnBlock(Production block) {
        return null;
    }

    @Override
    public boolean isDropSupported(Production p) {
        return p.supportDropVariableInstanciation();
    }

    public void refreshTextView(){
        tv.refreshText();
    }

}
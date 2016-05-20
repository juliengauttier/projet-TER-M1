package com.example.vladimirkarassouloff.projetter.ui.myelements.fonction.fonctionspeciale;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.example.vladimirkarassouloff.projetter.myelementsstring.ElementString;
import com.example.vladimirkarassouloff.projetter.myelementsstring.fonction.special.EteindreLed;
import com.example.vladimirkarassouloff.projetter.myelementsstring.fonction.special.Infrarouge;
import com.example.vladimirkarassouloff.projetter.ui.myelements.fonction.ElementFonction;
import com.example.vladimirkarassouloff.projetter.ui.myelementsproduction.Production;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vladimir on 20/05/2016.
 */
public class ElementInfrarougeGauche extends ElementFonction {

        public ElementInfrarougeGauche(Context context) {
        super(context, "InfrarougeGauche");
    }

        public ElementInfrarougeGauche(Context context, AttributeSet attrs) {
        super(context, attrs, "InfrarougeGauche");
    }


        @Override
        public List<View> onDraggedOnLine(View v) {
        List<View> ar = new ArrayList<View>();
        Production p = new Production(getContext(), new Infrarouge("InfrarougeGauche"));
        ar.add(p);
        return ar;
    }

        @Override
        public void onDropOver(Production block) {

    }

        @Override
        public ElementString onDraggedOnBlock(Production block) {
        return null;
    }

        @Override
        public boolean isDropSupported(Production p) {
        return false;
    }
}

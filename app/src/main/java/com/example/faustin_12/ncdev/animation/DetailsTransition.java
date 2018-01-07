package com.example.faustin_12.ncdev.animation;

import android.support.transition.ChangeBounds;
import android.support.transition.TransitionSet;

/**
 * Created by FAUSTIN-12 on 03/12/2017.
 */

public class DetailsTransition extends TransitionSet {
    public DetailsTransition(){
        setOrdering(ORDERING_TOGETHER);
        addTransition(new ChangeBounds());
    }
}

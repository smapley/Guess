package com.smapley.guess.holder;

import android.view.View;
import android.widget.EditText;

import com.smapley.guess.R;
import com.smapley.guess.adapter.AddTaskAdapter;
import com.smapley.guess.db.entity.NoteDetailsEntity;

/**
 * Created by smapley on 15/10/30.
 */
public class Add_Text_Holder extends BaseHolder {

    private EditText add_et_text;

    public Add_Text_Holder(View view) {
        super(view);
        add_et_text = (EditText) view.findViewById(R.id.add_et_text);
    }


    public void setData(final AddTaskAdapter adapter, final NoteDetailsEntity mode, final int position) {
        add_et_text.setText(mode.getText());
        add_et_text.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                mode.setText(add_et_text.getText().toString());
            }
        });
//        add_et_text.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//                if (add_et_text.isFocused()){
//                    LogUtil.d("---" + position);
//                    mode.setText(add_et_text.getText().toString());
//                }
//
//            }
//        });


    }
}

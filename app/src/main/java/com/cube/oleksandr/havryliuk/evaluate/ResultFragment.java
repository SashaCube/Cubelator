package com.cube.oleksandr.havryliuk.evaluate;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Objects;


public class ResultFragment extends Fragment implements View.OnLongClickListener {

    private static final int ONE_RESULT_FIELD = 1;
    private static final int TWO_RESULT_FIELD = 2;
    private static final int THREE_RESULT_FIELD = 3;

    private TextView mainResult;
    private TextView firstResult;
    private TextView secondResult;

    private View view;

    private int count = 0;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.result_fragment, container, false);

        secondResult = view.findViewById(R.id.tv_result_2);
        secondResult.setOnClickListener((MainActivity) getActivity());
        secondResult.setOnLongClickListener(this);
        firstResult = view.findViewById(R.id.tv_result_1);
        firstResult.setOnClickListener((MainActivity) getActivity());
        firstResult.setOnLongClickListener(this);
        mainResult = view.findViewById(R.id.tv_main_result);
        mainResult.setOnClickListener((MainActivity) getActivity());
        mainResult.setOnLongClickListener(this);

        split(mainResult);
        return view;
    }

    public void split(TextView currentResultField) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        params.weight = 1.0f;

        if (count == THREE_RESULT_FIELD) {
            count = ONE_RESULT_FIELD;
        } else {
            count++;
        }

        switch (count) {
            case THREE_RESULT_FIELD:
                secondResult.setLayoutParams(params);
                firstResult.setLayoutParams(params);
                mark(firstResult);
                break;

            case TWO_RESULT_FIELD:
                secondResult.setLayoutParams(params);
                mark(secondResult);
                break;

            case ONE_RESULT_FIELD:
                params.weight = 0.0f;
                params.width = 0;
                firstResult.setLayoutParams(params);
                secondResult.setLayoutParams(params);


                // save state of current(marked) result field
                if (currentResultField != mainResult) {
                    mainResult.setText(currentResultField.getText());
                }

                // clear another results fields
                firstResult.setText("");
                secondResult.setText("");

                mark(mainResult);
                break;
            default:
        }
    }

    public void mark(TextView view) {
        ((MainActivity) Objects.requireNonNull(getActivity())).setCurrentResultField(view);
        toDefault();
        view.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        view.setTextColor(getResources().getColor(R.color.white));
    }

    public void toDefault() {
        firstResult.setBackgroundColor(getResources().getColor(R.color.white));
        firstResult.setTextColor(getResources().getColor(R.color.colorPrimary));
        secondResult.setBackgroundColor(getResources().getColor(R.color.white));
        secondResult.setTextColor(getResources().getColor(R.color.colorPrimary));
        mainResult.setBackgroundColor(getResources().getColor(R.color.white));
        mainResult.setTextColor(getResources().getColor(R.color.colorPrimary));
    }

    public TextView getMainResult() {
        return mainResult;
    }

    public TextView getFirstResult() {
        return firstResult;
    }

    public TextView getSecondResult() {
        return secondResult;
    }

    @Override
    public boolean onLongClick(View v) {
        switch (v.getId()) {
            case (R.id.tv_result_1):
                if(firstResult != null)
                ((MainActivity) getActivity()).addNumber(firstResult.getText().toString());
                break;
            case (R.id.tv_result_2):
                if(secondResult != null)
                ((MainActivity) getActivity()).addNumber(secondResult.getText().toString());
                break;
            case (R.id.tv_main_result):
                if(mainResult != null)
                ((MainActivity) getActivity()).addNumber(mainResult.getText().toString());
                break;
            default:
        }
        return false;
    }
}

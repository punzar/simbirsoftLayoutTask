package com.simbirsoft.marat;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsFragment extends Fragment {


    public DetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_details,container,false);
        SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        String json = sharedPreferences.getString("EVENT","oops");

        TextView tvHead = view.findViewById(R.id.details_head_tv);
        TextView tvDate = view.findViewById(R.id.details_date_tv);
        TextView tvFoundation = view.findViewById(R.id.details_foundation_tv);
        TextView tvLocation = view.findViewById(R.id.details_location_tv);
        TextView tvPhone = view.findViewById(R.id.details_phone_tv);
        TextView tvBodyBegin = view.findViewById(R.id.details_article_begin_tv);
        TextView tvBodyEnd = view.findViewById(R.id.details_article_end_tv);
        TextView tvFAQ1 = view.findViewById(R.id.details_support_begin);
        TextView tvCountOfLike = view.findViewById(R.id.details_like_count);
        ImageView ivBody = view.findViewById(R.id.details_photo);
        ImageView iv1 = view.findViewById(R.id.details_1_iv);
        ImageView iv2 = view.findViewById(R.id.details_2_iv);
        ImageView iv3 = view.findViewById(R.id.details_3_iv);
        ImageView iv4 = view.findViewById(R.id.details_4_iv);
        ImageView iv5 = view.findViewById(R.id.details_5_iv);

        try {
            JSONObject object = new JSONObject(json);
            tvHead.setText(object.getString("titel"));
            tvDate.setText(object.getString("dateText"));
            tvFoundation.setText(object.getString("foundationName"));
            tvLocation.setText(object.getString("Location"));
            tvPhone.setText(object.getString("phoneNumber"));
            tvBodyBegin.setText(object.getString("articleText"));
            tvBodyEnd.setText(object.getString("articleTextEnd"));
            tvFAQ1.setText(object.getString("supportMessageBegin"));
            tvCountOfLike.setText(object.getString("countOfLike"));

            object.getString("photoHead");

            JSONArray jsonArray = object.getJSONArray("photoLikersPath");
            String[] photoArray = new String[jsonArray.length()];
            for(int i = 0; i<jsonArray.length();i++){
                photoArray[i] = (String) jsonArray.get(i);
            }



        } catch (JSONException e) {
            e.printStackTrace();
        }

        return view;

    }

}

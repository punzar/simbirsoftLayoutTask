package com.simbirsoft.marat;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.simbirsoft.marat.interfaces.FilterSettingsClickListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsFragment extends Fragment {


    public DetailsFragment() {
    }

    @Override
    public void onAttach(@NonNull final Context context) {
        super.onAttach(context);
        if (context instanceof HelpActivity) {
            HelpActivity activity = (HelpActivity) context;
            if (activity.getLifecycle().getCurrentState() == Lifecycle.State.RESUMED) {
                activity.hideBottomNavigation(false);
            }else {
                //not pretty but work
                //when orientation changing app catch Exception without this case
                final Handler handler = new Handler();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        HelpActivity activity = (HelpActivity) context;
                        while (!(activity.getLifecycle().getCurrentState() == Lifecycle.State.RESUMED)){

                        }
                        activity.hideBottomNavigation(false);
                    }
                });

            }

        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if(getActivity() instanceof HelpActivity){
            HelpActivity activity = (HelpActivity) getActivity();
            activity.hideBottomNavigation(true);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);
        Toolbar toolbar = view.findViewById(R.id.details_toolbar);
        toolbar.inflateMenu(R.menu.details_menu);
        toolbar.setNavigationIcon(R.drawable.ic_icon_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getActivity() instanceof HelpActivity)
                    getActivity().onBackPressed();
            }
        });
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return true;
            }
        });

        Bundle bundle = this.getArguments();
        NewsEvent newsEvent = bundle.getParcelable("NewsEvent");

//        SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
//        String json = sharedPreferences.getString("EVENT", "oops");

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


        tvHead.setText(newsEvent.getTitel());
        tvDate.setText(newsEvent.getDateText());
        tvFoundation.setText(newsEvent.getFoundationName());
        tvLocation.setText(newsEvent.getLocation());
        tvPhone.setText(newsEvent.getPhoneNumber());
        tvBodyBegin.setText(newsEvent.getArticleText());
        tvBodyEnd.setText(newsEvent.getArticleTextEnd());
        tvFAQ1.setText(newsEvent.getSupportMessageBegin());
        tvCountOfLike.setText(newsEvent.getCountOfLike());
        toolbar.setTitle(newsEvent.getTitel());

        ivBody.setImageDrawable(getDrawable(newsEvent.getPhotoHead()));
        ArrayList<String> photoPath = newsEvent.getPhotoLikersPath();
        iv1.setImageDrawable(getDrawable(photoPath.get(0)));
        iv2.setImageDrawable(getDrawable(photoPath.get(1)));
        iv3.setImageDrawable(getDrawable(photoPath.get(2)));
        iv4.setImageDrawable(getDrawable(photoPath.get(3)));
        iv5.setImageDrawable(getDrawable(photoPath.get(4)));

        return view;

    }

    private Drawable getDrawable(String photoName) {
        String pageName = getActivity().getPackageName();
        Resources resources = getActivity().getResources();
        int rID = resources.getIdentifier(photoName, "drawable", pageName);
        Drawable drawable = ContextCompat.getDrawable(getActivity(), rID);
        return drawable;
    }

}

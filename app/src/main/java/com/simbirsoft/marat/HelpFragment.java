package com.simbirsoft.marat;


import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.lang.ref.WeakReference;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class HelpFragment extends Fragment {
    private static final int NUM_OF_COLUMN = 2;
    private ArrayList<String> mTexts = new ArrayList<>();
    private ArrayList<Integer> mImages = new ArrayList<>();
    private ProgressBar mProgressBar;
    private View view;
    private RVTask mRvTask;


    public HelpFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_help, container, false);
        mProgressBar = view.findViewById(R.id.progress_bar_help);
        mRvTask = new RVTask();
        mRvTask.setHelpFragmentWeakReference(new WeakReference<HelpFragment>(HelpFragment.this));
        mRvTask.execute();

        return view;
    }

    private void initItems() {
            mTexts.add(getString(R.string.kids));
            mImages.add(R.drawable.little_girl);

            mTexts.add(getString(R.string.adults));
            mImages.add(R.drawable.mustachioed_man);

            mTexts.add(getString(R.string.elderly));
            mImages.add(R.drawable.granny);

            mTexts.add(getString(R.string.pets));
            mImages.add(R.drawable.cat);

            mTexts.add(getString(R.string.events));
            mImages.add(R.drawable.vans);
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_help);
        HelpReciclerViewAdapter helpReciclerViewAdapter = new HelpReciclerViewAdapter(view.getContext(), mTexts, mImages);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(NUM_OF_COLUMN, LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        recyclerView.setAdapter(helpReciclerViewAdapter);
    }

    void progressBarSwitch(){
        if(mProgressBar.getVisibility() == View.GONE){
            mProgressBar.setVisibility(View.VISIBLE);
        }else {
            mProgressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mRvTask.onCancelled();
    }



    static class RVTask extends AsyncTask<Void,Void,Void>{
        private WeakReference<HelpFragment> helpFragmentWeakReference;
        private boolean isCancel;

        void setHelpFragmentWeakReference(WeakReference<HelpFragment> helpFragmentWeakReference){
            this.helpFragmentWeakReference = helpFragmentWeakReference;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            try {
                helpFragmentWeakReference.get().progressBarSwitch();
            }catch (NullPointerException e){
                e.printStackTrace();
            }

        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Thread.sleep(5000);
                if(!isCancel) {
                    helpFragmentWeakReference.get().initItems();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            try {
                helpFragmentWeakReference.get().initRecyclerView();
                helpFragmentWeakReference.get().progressBarSwitch();

            }catch (NullPointerException e){
                e.printStackTrace();
            }
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            isCancel = true;

        }
    }

}

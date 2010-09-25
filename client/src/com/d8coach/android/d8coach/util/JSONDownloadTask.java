package com.d8coach.android.d8coach.util;

import org.json.JSONArray;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

public class JSONDownloadTask extends AsyncTask<String, Integer, JSONArray> { 
    private ProgressDialog progressDialog;
    private Activity mActivity;
	private JSONResponseProcessor mProcessor;
    public JSONDownloadTask(Activity activity, JSONResponseProcessor processor) {
        mActivity = activity;
        mProcessor = processor;
    }

    protected JSONArray doInBackground(String... urls) {
        JSONArray results = null;
        int count = urls.length;
//            long totalSize = 0;
        for (int i = 0; i < count; i++) {
//            	totalSize ++;
        
            results = new RestClient().fetchJson(urls[0].toString());
//                totalSize += Downloader.downloadFile(urls[i]);
//                publishProgress((int) ((i / (float) count) * 100));
        }
        return results;
    }

//        protected void onProgressUpdate(Integer... progress) {
//            setProgress(progress[0]);
//        }
    
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        showProgress(true);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        showProgress(false);
    }

    protected void onPostExecute(JSONArray results) {
        mProcessor.processResults(results);
        showProgress(false);
    }

    private void showProgress(boolean on) {
        mActivity.setProgressBarIndeterminateVisibility(on);
        if (on == true) {
//				progressDialog = ProgressDialog.show(
//                        mActivity.this,
//                        mResources.getString(R.string.loading_headlines),
//                        mResources.getString(R.string.wait_loading_headlines),
//                        true
//                );
            //progressDialog = new ProgressDialog(mActivity, R.style.DialogTheme);
            progressDialog = new ProgressDialog(mActivity);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setTitle(mProcessor.getLoadingTitle());
            progressDialog.setMessage(
                mActivity.getResources().getString(
                    mProcessor.getLoadingSubtitle()
                )
            );
            progressDialog.show();
        } else {
            //TODO clean up the memory in OnStop!
            progressDialog.dismiss();
        }
    }
}

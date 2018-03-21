//package com.okhttp3.activity;
//
//import android.os.Bundle;
//import android.view.View;
//import android.widget.ProgressBar;
//import android.widget.TextView;
//
//import com.okhttp3.R;
//import com.okhttp3.util.LogUtil;
//import com.okhttplib.HttpInfo;
//import com.okhttplib.OkHttpUtil;
//import com.okhttplib.callback.ProgressCallback;
//
//import base.BaseActivity;
//import butterknife.Bind;
//import butterknife.OnClick;
//
///**
// * 文件下载：支持批量下载、进度显示
// * @author zhousf
// */
//public class DownloadActivity extends BaseActivity {
//
//    private final String TAG = DownloadActivity.class.getSimpleName();
//    @Bind(R.id.tvResult)
//    TextView tvResult;
//    @Bind(R.id.downloadProgress)
//    ProgressBar downloadProgress;
//
//    /**
//     * 文件网络地址
//     */
//    private String mp4Url = "http://v.cctv.com/flash/mp4video6/TMS/2011/01/05/cf752b1c12ce452b3040cab2f90bc265_h264818000nero_aac32-1.mp4";
//    private final String requestTag = "download-tag-1001";//请求标识
//
//
//    @Override
//    protected int initLayout() {
//        return R.layout.activity_download;
//    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }
//
//
//    @OnClick({R.id.downloadBtn,R.id.downloadCancelBtn})
//    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.downloadBtn:
//                downloadFile();
//                break;
//            case R.id.downloadCancelBtn://取消请求
//                OkHttpUtil.getDefault().cancelRequest(requestTag);
//                break;
//        }
//    }
//
//
//    private void downloadFile() {
//        final HttpInfo info = HttpInfo.Builder()
//                .addDownloadFile(mp4Url, "myMP4", new ProgressCallback() {
//                    @Override
//                    public void onProgressMain(int percent, long bytesWritten, long contentLength, boolean done) {
//                        downloadProgress.setProgress(percent);
//                        tvResult.setText(percent+"%");
//                        LogUtil.d(TAG, "下载进度：" + percent);
//                    }
//
//                    @Override
//                    public void onResponseMain(String filePath, HttpInfo info) {
//                        tvResult.setText(info.getRetDetail());
//                        LogUtil.d(TAG, "下载结果：" + info.getRetDetail());
//                    }
//                })
//                .build();
//        OkHttpUtil.Builder()
//                .setReadTimeout(120)
//                .build(requestTag)//绑定请求标识
//                .doDownloadFileAsync(info);
//
//    }
//
//
//}

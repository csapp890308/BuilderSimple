package com.csapp.buildersample;

import android.content.Context;
import android.text.TextUtils;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.HashMap;

/**
 * Created by songyuqiang on 17/1/3.
 */
public class EhImageRequestBuilder {
    public int mHolder = R.drawable.icon_default_male;
    public int mErrorHolder = R.drawable.icon_default_male;
    public GlideUrl url;
    public static final int MALE_DEFAULT = R.drawable.icon_default_male;
    public static final int FEMALE_DEFAULT = R.drawable.icon_default_female;
    public static final int ECG_DEFAULT = 0;
    public String mSignal;
    public static final int THUMB_TYPE_MALE = 1;
    public static final int THUMB_TYPE_FEMALE = 2;
    private String mToken;

    public EhImageRequestBuilder setPlaceHolder(int drawableId) {
        this.mHolder = drawableId;
        return this;
    }

    public EhImageRequestBuilder setErrorHolder(int drawableId) {
        this.mErrorHolder = drawableId;
        return this;
    }



    public EhImageRequestBuilder setThumbSexType(int type) {
        switch (type) {
            case THUMB_TYPE_MALE:
                this.mHolder = MALE_DEFAULT;
                this.mErrorHolder = MALE_DEFAULT;
                break;
            case THUMB_TYPE_FEMALE:
                this.mHolder = FEMALE_DEFAULT;
                this.mErrorHolder = FEMALE_DEFAULT;
                break;
        }
        return this;
    }

    public EhImageRequestBuilder setSignal(String signal) {

        mSignal = signal;
        return this;
    }

    public EhImageRequestBuilder setAvatarUrlByUserHuid(String userHuid) {
        StringBuffer sb = new StringBuffer();
        sb.append(Const.AVATAR);
        sb.append(userHuid);
        sb.append(".jpg");
        GlideUrl tokenUrl = new GlideUrl(sb.toString(), () -> {
            HashMap<String, String> headers = new HashMap<>();
            if(!TextUtils.isEmpty(mToken)){
                headers.put("Authorization",""+mToken);
            }
            return headers;
        });

        this.url = tokenUrl;
        return this;
    }

    public DrawableRequestBuilder build(Context context) {
        return Glide.with(context)
                .load(this.url)
                .dontAnimate()
                .placeholder(this.mHolder)
                .error(this.mErrorHolder)
                .listener(new RequestListener<GlideUrl, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, GlideUrl s, Target<GlideDrawable> target, boolean b) {
                        if(e!=null){
                            e.printStackTrace();
                        }
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable glideDrawable, GlideUrl s, Target<GlideDrawable> target, boolean b, boolean b1) {
                        return false;
                    }
                });

    }


    public EhImageRequestBuilder setToken(String token) {
        mToken = token;
        return this;
    }
}

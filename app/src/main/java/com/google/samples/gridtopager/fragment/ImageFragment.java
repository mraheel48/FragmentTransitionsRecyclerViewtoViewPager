package com.google.samples.gridtopager.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.samples.gridtopager.R;

public class ImageFragment extends Fragment {

  private static final String KEY_IMAGE_RES = "com.google.samples.gridtopager.key.imageRes";

  public static ImageFragment newInstance(@DrawableRes int drawableRes) {
    ImageFragment fragment = new ImageFragment();
    Bundle argument = new Bundle();
    argument.putInt(KEY_IMAGE_RES, drawableRes);
    fragment.setArguments(argument);
    return fragment;
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    final View view = inflater.inflate(R.layout.fragment_image, container, false);

    Bundle arguments = getArguments();
    @DrawableRes int imageRes = arguments.getInt(KEY_IMAGE_RES);

    view.findViewById(R.id.image).setTransitionName(String.valueOf(imageRes));

    Glide.with(this)
        .load(imageRes)
        .listener(new RequestListener<Drawable>() {
          @Override
          public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable>
              target, boolean isFirstResource) {

            getParentFragment().startPostponedEnterTransition();
            return false;
          }

          @Override
          public boolean onResourceReady(Drawable resource, Object model, Target<Drawable>
              target, DataSource dataSource, boolean isFirstResource) {

            getParentFragment().startPostponedEnterTransition();
            return false;
          }
        })
        .into((ImageView) view.findViewById(R.id.image));
    return view;
  }
}

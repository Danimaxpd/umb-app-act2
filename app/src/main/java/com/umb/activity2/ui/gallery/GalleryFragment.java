package com.umb.activity2.ui.gallery;

import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.textfield.TextInputEditText;
import com.umb.activity2.databinding.FragmentGalleryBinding;

import java.util.Objects;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    private FragmentGalleryBinding binding;
    private TextInputEditText userUrlText;
    private Button btnSearchUrl;
    private Editable urlWb;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);
        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        userUrlText = binding.wViewUserUmbUrl;
        btnSearchUrl = binding.btnUrlWbSearch;
        urlWb = Objects.requireNonNull(userUrlText.getText());
        btnSearchUrl.setOnClickListener(v -> {
            urlWb = userUrlText.getText();
            setPageAndLoad(binding, urlWb);
        });

        setPageAndLoad(binding, urlWb);

        return root;
    }

    private void setPageAndLoad(FragmentGalleryBinding binding, Editable url)
    {
        final WebView view = binding.wViewUserUmb;
        view.setWebViewClient(new WebViewClient());
        view.getSettings().setJavaScriptEnabled(true);
        view.loadUrl("https://" + url);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
package polytech.unice.fr.polynews.fragment.info;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import polytech.unice.fr.polynews.R;

/**
 * @version on 22/04/16.
 */
public class ContactFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//<<<<<<< HEAD
        View v =inflater.inflate(R.layout.layout_contact,container,false);
        WebView contact = (WebView) v.findViewById(R.id.webview_contact);
        contact.loadUrl("https://informatique.polytechnice.fr/jahia/webdav/site/informatique/shared/EC5.html");
//=======
//        View v =inflater.inflate(R.layout.layout_contact_assos,container,false);
//>>>>>>> 3fd5e000f17d4340f6ce8428de4a9786b5aa15e1
        return v;
    }
}

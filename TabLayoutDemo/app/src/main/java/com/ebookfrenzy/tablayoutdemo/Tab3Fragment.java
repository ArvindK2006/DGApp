package com.ebookfrenzy.tablayoutdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.ebookfrenzy.tablayoutdemo.databinding.FragmentSignupBinding;
import com.ebookfrenzy.tablayoutdemo.databinding.FragmentTab3Binding;
//import com.sun.mail.iap.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Tab3Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Tab3Fragment extends Fragment {

    Button button;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Tab3Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Tab1Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Tab3Fragment newInstance(String param1, String param2) {
        Tab3Fragment fragment = new Tab3Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        //  TextView tv = (TextView) findViewById(R.id.text_view_result);

       // String url = "https://www.googleapis.com/calendar/v3/calendars/arvind.kakanavaram@gmail.com/events?key=AIzaSyDfy9Y4PaNilXfzGytXAeGZId0rKc25Yrc";


    }

    public void openSignUpActivity(){
        Intent intent = new Intent(getActivity(),SignUpActivity.class);
        startActivity(intent);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tab3, container, false);

        // When the button is clicked, the fragment_signup screen is shown
        // however, the rest of the app is still visible and interactive
       // TextView tv = (TextView) view.findViewById(R.id.text_view_result);


        TextView tv = (TextView) view.findViewById(R.id.text_view_result);

        String url = "https://www.googleapis.com/calendar/v3/calendars/arvind.kakanavaram@gmail.com/events?key=AIzaSyDfy9Y4PaNilXfzGytXAeGZId0rKc25Yrc";

       //String url = "https://jsonplaceholder.typicode.com/todos/1";
        //uses this...
      //  String url = "https://www.googleapis.com/calendar/v3/calendars/arvindrahil1@gmail.com/events?key=AIzaSyDZG1npYnrpLoaKugWo3lgycvvbZREAktg";

       JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
           // public void onResponse(String response) {

                try {

                    //int userId = response.getInt("userId");
                    //int id = response.getInt("id");
                    //String title = response.getString("title");
                    //boolean completed = response.getBoolean("completed");

                    //tv.setText(userId + "\n" + id + "\n" + title + "\n" + completed);

                    JSONArray jsonArray = response.getJSONArray("items");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject item = jsonArray.getJSONObject(i);
                        if (item.has("summary")) {

                            String summary = item.getString("summary");
                            if (summary.toLowerCase(Locale.ROOT).contains("work".toLowerCase(Locale.ROOT))) {
                                String created = item.getString("created");

                                DateFormat fmt;
                                if (created.endsWith("Z")) {
                                    fmt = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
                                } else {
                                    fmt = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
                                }

                                tv.append(summary + ", " + created + "\n\n");
                            }
                        }
                       // tv.append( ", " + created + "\n\n");

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                //tv.setText(response.toString());

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                tv.setText("error ");
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        requestQueue.add(jsonObjectRequest);

        return view;

    }
}
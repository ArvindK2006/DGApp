package com.ebookfrenzy.tablayoutdemo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
//import com.ebookfrenzy.tablayoutdemo.databinding.FragmentSignupBinding;
//import com.ebookfrenzy.tablayoutdemo.databinding.FragmentTab3Binding;
//import com.sun.mail.iap.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EventsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EventsFragment extends Fragment {

    Button button;
    Button toSignUp;

    private String selectedFromList;
    ArrayList<String> myList = new ArrayList<String>();
    ArrayList<String> allData = new ArrayList<String>();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public EventsFragment() {
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
    public static EventsFragment newInstance(String param1, String param2) {
        EventsFragment fragment = new EventsFragment();
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

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_events, container, false);

        ListView events = view.findViewById(R.id.text_view_result);
        Button toSignUp = (Button) view.findViewById(R.id.switchButton);
        toSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toSignUp.setVisibility(View.GONE);
                events.setVisibility(View.GONE);

                SignUpFragment suf = new SignUpFragment();
                Bundle args = new Bundle();
                    args.putString("sendEvent", selectedFromList);
                    suf.setArguments(args);

                    FragmentTransaction fr = getFragmentManager().beginTransaction();
                    fr.replace(R.id.fragment_container, new SignUpFragment());
                    fr.replace(R.id.fragment_container, suf);

                    fr.addToBackStack(null);
                    fr.commit();


            }
        });

        events.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int index = i;
                int selectedValue = index;
                //selectedFromList =(events.getItemAtPosition(index).toString());
                if(index >= 0){
                    selectedFromList =(allData.get(index).toString());
                }
            }
        });


        ListView tv = view.findViewById(R.id.text_view_result);

        String url = "https://www.googleapis.com/calendar/v3/calendars/arvind.kakanavaram@gmail.com/events?key=AIzaSyDfy9Y4PaNilXfzGytXAeGZId0rKc25Yrc";

       JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
           // public void onResponse(String response) {

                try {

                    JSONArray jsonArray = response.getJSONArray("items");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject item = jsonArray.getJSONObject(i);
                        if (item.has("summary")) {

                            String summary = item.getString("summary");
                            if (summary.toLowerCase(Locale.ROOT).contains("work".toLowerCase(Locale.ROOT))) {
                                String created = item.getString("created");

                                String iCalUID = item.getString("iCalUID");
                                String html = item.getString("htmlLink");
                                allData.add(summary + ", " + created + ", " + iCalUID + ", " + html + "\n\n");
                                myList.add(summary + ", " + created + "\n\n");
                            }
                        }

                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity().getApplicationContext(),
                            android.R.layout.simple_list_item_1, myList);
                    events.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //tv.setText("error ");
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        requestQueue.add(jsonObjectRequest);

        return view;

    }
}
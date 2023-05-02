package com.ebookfrenzy.tablayoutdemo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignUpFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignUpFragment extends Fragment {

    EditText txtEmail, txtMessage;
    Button btnSend;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

  /* Session session;
    Properties props;
    final String username = "raghus06@yahoo.com";*/

    public SignUpFragment() {
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
    public static SignUpFragment newInstance(String param1, String param2) {
        SignUpFragment fragment = new SignUpFragment();
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

            /*txtEmail = txtEmail.findViewById(R.id.txtEmail);
            txtMessage = txtMessage.findViewById(R.id.txtMessage);
            btnSend = btnSend.findViewById(R.id.btnSend);*/


           // final String username = "raghus06@yahoo.com";
        }
        //View.active
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_signup, container, false);

        Button goBack = view.findViewById(R.id.backToEvents);
        Button btnSend = (Button) view.findViewById(R.id.btnSend);
        EditText email = view.findViewById(R.id.txtEmail);
        TextView displayEvent = view.findViewById(R.id.display);



        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack.setVisibility(View.GONE);
                btnSend.setVisibility(View.GONE);
                email.setVisibility(View.GONE);
                displayEvent.setVisibility(View.GONE);

                Tab3Fragment fragThree = new Tab3Fragment();

                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new Tab3Fragment());
                fr.replace(R.id.fragment_container, fragThree);

                fr.addToBackStack(null);
                fr.commit();

                /*Tab3Fragment frg = null;
                frg = (Tab3Fragment) getFragmentManager().findFragmentByTag("fragment_tab3");
                final FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.detach(frg);
                ft.attach(frg);
                ft.commit();*/ //Crashing app -- continue trying with this

            }
        });

        Bundle args = getArguments();
        String eventDisplayed = args.getString("test");
        //args.get

        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_tab2, container, false);

        TextView display = (TextView) view.findViewById(R.id.display);
        display.setText(eventDisplayed);

        //display.setText();

        //setContentView(View.id);


        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    // here you set what you want to do when user clicks your button,
                    // e.g. launch a new activity
                    final String username = "raghus06@yahoo.com";
                    final String password = "eqqkkevzohdkbaon";

                    //EditText messageToSend = (EditText) view.findViewById(R.id.txtMessage);
                    //String body = messageToSend.getText().toString();
                    String body = eventDisplayed;

                    EditText txtEmail = (EditText) view.findViewById(R.id.txtEmail);
                    String email = txtEmail.getText().toString();

                    Properties props = new Properties();
                    props.put("mail.smtp.auth", "true");
                    //props.put("mail.smtp.starttls.enable", "true");
                    props.put("mail.smtp.ssl.enable", "true");
                    props.put("mail.smtp.host", "smtp.mail.yahoo.com");
                    props.put("mail.smtp.port", "465");

                    Session session = Session.getInstance(props, new Authenticator(){
                        protected PasswordAuthentication getPasswordAuthentication(){
                            return new PasswordAuthentication(username, password);
                        }
                    });


                    try {
                        Message message = new MimeMessage(session);
                        message.setFrom(new InternetAddress(username));
                        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
                        message.setSubject("Thank you for signing up!");
                        message.setText(body);
                        // Transport.send(message);
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Transport.send(message);//send(message);
                                } catch (MessagingException e) {
                                    e.printStackTrace();
                                }
                            }
                        }).start();
                        Toast.makeText(getActivity().getApplicationContext(), "Email sent successfully.", Toast.LENGTH_LONG).show();
                    } catch (MessagingException e) {
                        throw new RuntimeException(e);
                    }

                } catch (Exception ex) {
                    String msg = ex.getMessage();
                }
            }
        });
        return view;
    }
}
package com.zhcet.zhcetnavigationapp;


 import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

 import com.google.firebase.database.DatabaseReference;
 import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Shantanu on 02-04-2017.
 */

public class computer extends Fragment{
    View myview;
    ListView simpleList;
    FirebaseDatabase mReference;
    private DatabaseReference mfirebase,sfirebase;

    static TeacherStatus abc=new TeacherStatus();
    //Context context;
    //CustomAdapter listAdapter;

    public static int [] teacherImages={R.drawable.sumar,R.drawable.nesarahmad,R.drawable.abbas,R.drawable.sufiyan,R.drawable.mrwarsi,R.drawable.izharuddin,R.drawable.rashidali,R.drawable.saifulislam,R.drawable.nadeemakhtar,R.drawable.abdulqadeer,R.drawable.tameemahmad,R.drawable.asadmohdkhan,R.drawable.muneebhasan,R.drawable.faisalalam,R.drawable.amu,R.drawable.amu,R.drawable.amu,R.drawable.amu,R.drawable.amu,R.drawable.amu,R.drawable.amu};
    public static String [] teacherNameList={"Prof. Mohammad Sarosh Umar","Prof. Nesar Ahmad","Prof. Ash Mohammad Abbas","Prof. M. M. Sufyan Beg","Mr. Misbahur Rahman Warsi","Dr. Izharuddin","Dr. Rashid Ali","Dr. Saiful Islam","Mr. Nadeem Akhtar","Mr. Mohammed Abdul Qadeer","Mr. Tameem Ahmad","Mr. Asad Mohammad Khan","Mr. Muneeb Hasan Khan","Mr. Faisal Alam","Ms. Zeba Khanam","Ms. Hira Javed","Mr. Mohd. Imran","Mr. Misbahul Haque","Ms. Syeda Shira Moin","Mr. Mohd. Shoaib","Mr. Syed Usman Ahmad"};

    public static String[] getstatus={"Active Now",abc.getStatus(),abc.getStatus(),abc.getStatus(),abc.getStatus(),abc.getStatus(),abc.getStatus(),abc.getStatus(),abc.getStatus(),abc.getStatus(),abc.getStatus(),abc.getStatus(),abc.getStatus(),abc.getStatus(),abc.getStatus(),abc.getStatus(),abc.getStatus(),abc.getStatus(),abc.getStatus(),abc.getStatus(),abc.getStatus()};
   String cc="false";
    public String setemail(String status){
        return status;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Computer");

        mfirebase= FirebaseDatabase.getInstance().getReference().child("Users").child("Register");

        simpleList = (ListView) view.findViewById(R.id.simpleListView);
        final CustomAdapter customAdapter = new CustomAdapter(getActivity().getApplicationContext(), teacherNameList, teacherImages, getstatus);
        simpleList.setAdapter(customAdapter);

        simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.v("position"," "+position);

                //mfirebase=mfirebase.child();
               // int value = (int)customAdapter.getItem(position);
                if(position==0){
                    Coordinate cr;
                    cr = new Coordinate("Sarosh Omar", "27.9157335", "78.0798319","false");
                    Intent gotomap=new Intent();
                    gotomap.setClass(getContext(),LocationActivity.class);

                    UserInformation user=new UserInformation();
                 /*   if(user.isStatus()){
                        cr = new Coordinate("Sarosh Omar",user.getLatitude(),user.getLongitude());
                        Log.v("Hello",user.getLatitude());
                    }*/
                    gotomap.putExtra("Editing",cr);
                    startActivity(gotomap);
                }
                else if(position==1){
                    Intent gotomap=new Intent();
                    gotomap.setClass(getContext(),LocationActivity.class);
                    Coordinate cr=new Coordinate("Nesar Ahmad","27.9158089","78.0797263","false");
                    gotomap.putExtra("Editing",cr);
                    startActivity(gotomap);
                }

                else if(position==2){
                    Coordinate cr = new Coordinate("Ash Mohd. Abbas", "27.9157299", "78.0797595","false");
                    Intent gotomap = new Intent();
                    gotomap.setClass(getContext(), LocationActivity.class);
                    gotomap.putExtra("Editing", cr);
                    startActivity(gotomap);
                }
                else if(position==3){
                    Coordinate cr = new Coordinate("M. M. Sufyan Beg", "27.9152182", "78.0798490","false");
                    Intent gotomap = new Intent();
                    gotomap.setClass(getContext(), LocationActivity.class);
                    gotomap.putExtra("Editing", cr);
                    startActivity(gotomap);
                }
                else if(position==4){
                   // Toast.makeText(getContext(), "selected", Toast.LENGTH_SHORT).show();
                    Coordinate cr = new Coordinate("M.R.Warsi", "27.9159081", "78.0798145","false");
                    Intent gotomap = new Intent();
                    gotomap.setClass(getContext(), LocationActivity.class);
                    gotomap.putExtra("Editing", cr);
                    startActivity(gotomap);

                }
                else if(position==5){
                    //Toast.makeText(getContext(), "selected", Toast.LENGTH_SHORT).show();
                    Coordinate cr = new Coordinate("Dr.Izharuddin", "27.9155473", "78.0801416","false");
                    Intent gotomap = new Intent();
                    gotomap.setClass(getContext(), LocationActivity.class);
                    gotomap.putExtra("Editing", cr);
                    startActivity(gotomap);
                }
                else if(position==6){
                   // Toast.makeText(getContext(), "selected", Toast.LENGTH_SHORT).show();
                    Coordinate cr = new Coordinate("Rashid Ali ", "27.9155952 ", "78.0800012 ","false");
                    Intent gotomap = new Intent();
                    gotomap.setClass(getContext(), LocationActivity.class);
                    gotomap.putExtra("Editing", cr);
                    startActivity(gotomap);
                }
                else if(position==7){
                    Coordinate cr = new Coordinate("Saiful Islam ", "27.9154762 ", "78.0799787 ","false");
                    Intent gotomap = new Intent();
                    gotomap.setClass(getContext(), LocationActivity.class);
                    gotomap.putExtra("Editing", cr);
                    startActivity(gotomap);
                }

                //position 8 is not now in the department...........
                else if(position==8){
                    Coordinate cr = new Coordinate("Nadeem Akhtar", "27.9152466", "78.0799076","false");
                    Intent gotomap = new Intent();
                    gotomap.setClass(getContext(), LocationActivity.class);
                    gotomap.putExtra("Editing", cr);
                    startActivity(gotomap);
                }

                else if(position==9){
                    Coordinate cr = new Coordinate("Abdul Qadeer ", "27.9153708 ", "78.0800533 ","false");
                    Intent gotomap = new Intent();
                    gotomap.setClass(getContext(), LocationActivity.class);
                    gotomap.putExtra("Editing", cr);
                    startActivity(gotomap);
                }
                else if(position==10){
                    Coordinate cr = new Coordinate("Tameem Ahmed", "27.9153659", "78.0799854","false");
                    Intent gotomap = new Intent();
                    gotomap.setClass(getContext(), LocationActivity.class);
                    gotomap.putExtra("Editing", cr);
                    startActivity(gotomap);
                }
                else if(position==11){
                    Coordinate cr = new Coordinate("Asad M Khan", "27.9143441", "78.0817736","false");
                    Intent gotomap = new Intent();
                    gotomap.setClass(getContext(), LocationActivity.class);
                    gotomap.putExtra("Editing", cr);
                    startActivity(gotomap);
                }
                else if(position==12){
                    Coordinate cr = new Coordinate("Muneeb Hasan Khan", "27.9145803", "78.0803474","false");
                    Intent gotomap = new Intent();
                    gotomap.setClass(getContext(), LocationActivity.class);
                    gotomap.putExtra("Editing", cr);
                    startActivity(gotomap);
                }
                else if(position==13){
                    //Faisal
                    Coordinate cr = new Coordinate("Faisal Alam","27.9148803","78.0805444","false");
                    Intent gotomap = new Intent();
                    gotomap.setClass(getContext(), LocationActivity.class);
                    gotomap.putExtra("Editing", cr);
                    startActivity(gotomap);
                }
                else if(position==14){
                    //Zeba
                    Coordinate cr = new Coordinate();
                    Intent gotomap = new Intent();
                    gotomap.setClass(getContext(), LocationActivity.class);
                    gotomap.putExtra("Editing", cr);
                    startActivity(gotomap);
                }
                else if(position==15){
                    //hira javed
                    Coordinate cr = new Coordinate();
                    Intent gotomap = new Intent();
                    gotomap.setClass(getContext(), LocationActivity.class);
                    gotomap.putExtra("Editing", cr);
                    startActivity(gotomap);
                }
                else if(position==16){
                    //Imran
                    Coordinate cr = new Coordinate();
                    Intent gotomap = new Intent();
                    gotomap.setClass(getContext(), LocationActivity.class);
                    gotomap.putExtra("Editing", cr);
                    startActivity(gotomap);
                }
                else if(position==17){
                    //Misbah
                    Coordinate cr = new Coordinate();
                    Intent gotomap = new Intent();
                    gotomap.setClass(getContext(), LocationActivity.class);
                    gotomap.putExtra("Editing", cr);
                    startActivity(gotomap);
                }
                else if(position==18){
                    //Shira
                    Coordinate cr = new Coordinate();
                    Intent gotomap = new Intent();
                    gotomap.setClass(getContext(), LocationActivity.class);
                    gotomap.putExtra("Editing", cr);
                    startActivity(gotomap);
                }
                else if(position==19){
                    //shoib
                    Coordinate cr = new Coordinate();
                    Intent gotomap = new Intent();
                    gotomap.setClass(getContext(), LocationActivity.class);
                    gotomap.putExtra("Editing", cr);
                    startActivity(gotomap);
                }
                else if(position==20){
                    //usman
                    Coordinate cr = new Coordinate();
                    Intent gotomap = new Intent();
                    gotomap.setClass(getContext(), LocationActivity.class);
                    gotomap.putExtra("Editing", cr);
                    startActivity(gotomap);
                }

            }
        });
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myview= inflater.inflate(R.layout.activity_computer, container, false);
        return myview;
    }


}
package com.example.saisandeep.fragmenttransactionexample;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class MyActivity extends Activity implements FragmentManager.OnBackStackChangedListener {

    FragmentManager man;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        man=getFragmentManager();
        tv= (TextView) findViewById(R.id.message);
        man.addOnBackStackChangedListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void AddA(View v)
    {
        FragmentA f1=new FragmentA();
        FragmentTransaction tra=man.beginTransaction();
        tra.add(R.id.my_layout,f1,"A");
        tra.addToBackStack("addA");
        tra.commit();
    }
    public void RemoveA(View v)
    {

        FragmentA f1= (FragmentA) man.findFragmentByTag("A");
        FragmentTransaction tra=man.beginTransaction();

        if(f1 != null)
        {
           tra.remove(f1);
            tra.addToBackStack("RemoveA");
            tra.commit();
        }
        else
        {
            Toast.makeText(this,"Fragment A is not created",Toast.LENGTH_SHORT).show();
        }
    }
    public void AddB(View v)
    {

        FragmentB f2=new FragmentB();
        FragmentTransaction tra=man.beginTransaction();
        tra.add(R.id.my_layout,f2,"B");
        tra.addToBackStack("addB");
        tra.commit();
    }
    public void RemoveB(View v)
    {

        FragmentB f2= (FragmentB) man.findFragmentByTag("B");
        FragmentTransaction tra=man.beginTransaction();

        if(f2 != null)
        {
            tra.remove(f2);
            tra.addToBackStack("RemoveB");
            tra.commit();
        }
        else
        {
            Toast.makeText(this,"Fragment B is not created",Toast.LENGTH_SHORT).show();
        }
    }
    public void ReplaceAwithB(View v)
    {

        FragmentB f2=new FragmentB();
        FragmentTransaction tra=man.beginTransaction();
        tra.replace(R.id.my_layout,f2,"B");
        tra.addToBackStack("ReplaceAwithB");
        tra.commit();
    }
    public void ReplaceBwithA(View v)
    {

        FragmentA f1=new FragmentA();
        FragmentTransaction tra=man.beginTransaction();
        tra.replace(R.id.my_layout,f1,"A");
        tra.addToBackStack("ReplaceBwithA");
        tra.commit();
    }
    public void AttachA(View v)
    {

        FragmentA f1= (FragmentA) man.findFragmentByTag("A");
        FragmentTransaction tra=man.beginTransaction();

        if(f1 != null)
        {
            tra.attach(f1);
            tra.addToBackStack("AttachA");
            tra.commit();
        }
    }
    public void DetachA(View v)
    {

        FragmentA f1= (FragmentA) man.findFragmentByTag("A");
        FragmentTransaction tra=man.beginTransaction();

        if(f1 != null)
        {
            tra.detach(f1);
            tra.addToBackStack("DetachA");
            tra.commit();
        }
    }


    public void Back(View v)
    {

        man.popBackStack();
    }

    public void Popup(View v)
    {

        man.popBackStack("addA",0);
    }

    @Override
    public void onBackStackChanged() {

        tv.setText(tv.getText()+"\n");
        tv.setText(tv.getText()+"The current status of Back Stack");
        tv.setText(tv.getText()+"\n");

        int count=man.getBackStackEntryCount();

        for(int i=count-1;i>=0;i--)
        {
            FragmentManager.BackStackEntry entry= man.getBackStackEntryAt(i);
            tv.setText(tv.getText()+" "+entry.getName()+"\n");

        }

        tv.setText(tv.getText()+"\n");
    }
}

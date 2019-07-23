package com.example.bazaruno;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class For_Man_Clothes extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView; // use to show the bottom list of icon on main screen
    RecyclerView recyclerView;
    ExpandableHeightGridView girdview;
    int double_click=0;
    int doub_sub_click=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for__man__clothes);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // code for Recycleview for horzinal scroll of items
        recyclerView=(RecyclerView) findViewById(R.id.recycleview);
        Bazar_List_View_Handler();

        // code for girdview for vertical scroll of items
        girdview=(ExpandableHeightGridView) findViewById(R.id.girdview);
        Bazar_Clothing_Gird();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Any thing you can do with this...", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.getDrawerArrowDrawable().setColor(Color.parseColor("#ff6969"));
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        // details or properties of bottom list bar items like search more etc
        bottomNavigationView=(BottomNavigationView) findViewById(R.id.bottom_nav_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.getMenu().getItem(0).setCheckable(false);
        bottomNavigationView.getMenu().getItem(1).setCheckable(false);
        bottomNavigationView.getMenu().getItem(2).setCheckable(false);
        bottomNavigationView.getMenu().getItem(3).setCheckable(false);

        // ########################## For Man Section Code #############################//

        // This function work for show and hiding nav list and change image
        Parent_Controller(R.id.fashion_and_wear,R.id.fashion_drop,R.id.fashion_and_wear_layout);
        // This function work for show and hiding sub category of For Man (like Shoes,Accessories)
        Child_Controller(R.id.for_main_head_layout,R.id.for_main_sub_layout,R.id.for_main_image,R.id.line_for_main);
        // Work for user click and send intent to another activity
        Intent_For_Man_Section();


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    // work for toolbar item such as account and search

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.search) {
          Intent intent=new Intent(getApplicationContext(),Search_Activity.class);
          startActivity(intent);
        }
        else if (id == R.id.login){
           Intent intent=new Intent(getApplicationContext(),Account_Activity.class);
           startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    // this works for slide naviagation item and their selection

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        // function to be run for listining call of bottom naviagation items

        Bottom_Naviation_Item_Listner(id);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    // this function works for bottom item icom menu such search, compare etc

    void  Bottom_Naviation_Item_Listner(int id)
    {
        if (id == R.id.search)
        {
            Intent intent=new Intent(getApplicationContext(),Search_Activity.class);
            startActivity(intent);
        }
        else if (id == R.id.compare)
        {
            Intent intent=new Intent(getApplicationContext(),Compare_Activity.class);
            startActivity(intent);
        }
        else if(id == R.id.favorites)
        {
            Intent intent=new Intent(getApplicationContext(),Favorites.class);
            startActivity(intent);
        }
        else if (id == R.id.more)
        {
            Intent intent=new Intent(getApplicationContext(),More_Option.class);
            startActivity(intent);
        }
    }

    // use for showing show hide content of fashion and wear

    void Parent_Controller(int Image_id, int head_LinearLayout_id, int child_linearlayout_id){

        final ImageView imageView=(ImageView) findViewById(Image_id);
        final LinearLayout fashion_drop=(LinearLayout) findViewById(head_LinearLayout_id);
        final LinearLayout fashion_and_wear_layout=(LinearLayout) findViewById(child_linearlayout_id);
        fashion_and_wear_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (double_click==0){
                    imageView.setImageResource(R.drawable.down_arrow);
                    fashion_drop.setVisibility(View.VISIBLE);
                    fashion_and_wear_layout.setBackgroundColor(getResources().getColor(R.color.back));
                    double_click=1;
                }
                else if (double_click == 1)
                {
                    imageView.setImageResource(R.drawable.greater);
                    fashion_and_wear_layout.setBackgroundColor(Color.WHITE);
                    fashion_drop.setVisibility(View.GONE);

                    // set sub session to normal state
                    Set_To_Normal_State(R.id.for_main_sub_layout,R.id.line_for_main,R.id.for_main_head_layout,R.id.for_main_image);
                    double_click=0;
                }
            }
        });
    }


    // use for show sub categories such for man and its details
    void Child_Controller(int head,int sub,int sub_image,int sub_line){

        final LinearLayout for_man_head_layout=(LinearLayout) findViewById(head);
        final LinearLayout for_man_sub_layout=(LinearLayout) findViewById(sub);
        final TextView for_man_line=(TextView) findViewById(sub_line);
        final ImageView for_main_image=(ImageView) findViewById(sub_image);

        for_man_head_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (doub_sub_click==0){
                    for_main_image.setImageResource(R.drawable.sub_icon);
                    for_man_line.setVisibility(View.VISIBLE);
                    for_man_head_layout.setBackgroundColor(Color.parseColor("#E8ECE8"));
                    for_man_sub_layout.setBackgroundColor(Color.parseColor("#E8ECE8"));
                    for_man_sub_layout.setVisibility(View.VISIBLE);
                    doub_sub_click=1;
                }
                else if (doub_sub_click == 1)
                {
                    for_main_image.setImageResource(R.drawable.add_icon);
                    for_man_sub_layout.setVisibility(View.GONE);
                    for_man_head_layout.setBackgroundColor(getResources().getColor(R.color.back));
                    for_man_sub_layout.setBackgroundColor(getResources().getColor(R.color.back));
                    for_man_line.setVisibility(View.GONE);
                    doub_sub_click=0;
                }

            }
        });

    }

    // Set the properities of child list to normal when parent list is close
    void Set_To_Normal_State(int sub_Linear_layout_id,int Text_line_id,int head_linear_Layout,int sub_image){

        TextView textView=(TextView) findViewById(Text_line_id);
        textView.setVisibility(View.GONE);
        LinearLayout linearLayout=(LinearLayout) findViewById(sub_Linear_layout_id);
        linearLayout.setVisibility(View.GONE);
        LinearLayout head=(LinearLayout) findViewById(head_linear_Layout);
        head.setBackgroundColor(getResources().getColor(R.color.back));
        ImageView imageView=(ImageView) findViewById(sub_image);
        imageView.setImageResource(R.drawable.add_icon);
    }


    // This function will simply send the intent to another activity
    void Intent_Handler(Class activitys){

        Intent intent=new Intent(getApplicationContext(),activitys);
        startActivity(intent);
    }

    void  Intent_For_Man_Section(){
        ArrayList<Integer> ids_for_man_section=new ArrayList<>();
        ids_for_man_section.add(R.id.clothes_for_man); // id of clothing
        ids_for_man_section.add(R.id.shoes_for_man); // id of shoes
        ids_for_man_section.add(R.id.accessories_for_man); // id of accessories
        ids_for_man_section.add(R.id.beauity_and_care_for_man); // id of beauity and care

        // Run when clother item is click or selected
        LinearLayout clother_for_man=(LinearLayout) findViewById(ids_for_man_section.get(0));
        clother_for_man.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent_Handler(For_Man_Clothes.class);
            }
        });
    }


    void Bazar_List_View_Handler()
    {
        Clothes_Recycler_Data_Container container=new Clothes_Recycler_Data_Container();
        container.setImage("https://files.guidedanmark.org/files/484/19792_Bazar_Vest_-_Frugtmarked.jpg");
        container.setName("University Road");

        Clothes_Recycler_Data_Container container1=new Clothes_Recycler_Data_Container();
        container1.setImage("https://media.dhakatribune.com/uploads/2016/12/20161226-RajibDhar-2459-1.jpg");
        container1.setName("Saddar Bazar");

        Clothes_Recycler_Data_Container container2=new Clothes_Recycler_Data_Container();
        container2.setImage("https://media-cdn.tripadvisor.com/media/photo-s/06/f4/34/f0/zelyony-bazaar.jpg");
        container2.setName("Ring Road");

        Clothes_Recycler_Data_Container container3=new Clothes_Recycler_Data_Container();
        container3.setImage("https://islamicplaces.files.wordpress.com/2011/03/bazar-in-damascus-syria.jpg");
        container3.setName("Dalazak road");

        Clothes_Recycler_Data_Container container4=new Clothes_Recycler_Data_Container();
        container4.setImage("https://media.dhakatribune.com/uploads/2016/12/20161226-RajibDhar-2459-1.jpg");
        container4.setName("Qissa Khawani");



        // List of Data object for Recycle view list
        ArrayList<Clothes_Recycler_Data_Container> list=new ArrayList<>();
        // add item object to the list
        list.add(container);
        list.add(container1);
        list.add(container2);
       list.add(container3);
        list.add(container4);


        // Calling adapter of recycle view
        Clothes_RecycleViw adapter=new Clothes_RecycleViw(this,list);

        // Finding and adding details of the recyclerview in android
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }

    // use for creating gird of items

    void Bazar_Clothing_Gird()
    {
       ArrayList<Clothes_Recycler_Data_Container> list=new ArrayList<>();

       Clothes_Recycler_Data_Container container=new Clothes_Recycler_Data_Container();
       container.setName("T-Shirts");
       container.setImage("https://www.stickpng.com/assets/images/580b57fbd9996e24bc43bf73.png");

        Clothes_Recycler_Data_Container container1=new Clothes_Recycler_Data_Container();
        container1.setName("Shirts");
        container1.setImage("https://4.imimg.com/data4/TW/QS/MY-13230786/men-formal-shirts-250x250.jpg");

        Clothes_Recycler_Data_Container container2=new Clothes_Recycler_Data_Container();
        container2.setName("Pants");
        container2.setImage("https://www.pngkey.com/png/detail/761-7610763_pants-ripped-jeans-rippedjeans-clothes-niche-nichememe-jungkook.png");

        Clothes_Recycler_Data_Container container3=new Clothes_Recycler_Data_Container();
        container3.setName("Sports");
        container3.setImage("https://www.egosportthailand.com/web/wp-content/uploads/2018/01/EG3004-1-741x900.jpg");

        Clothes_Recycler_Data_Container container4=new Clothes_Recycler_Data_Container();
        container4.setName("Suits and Coats");
        container4.setImage("https://i.ya-webdesign.com/images/jacket-clipart-mens-suit-5.jpg");

        Clothes_Recycler_Data_Container container5=new Clothes_Recycler_Data_Container();
        container5.setName("Shalwar Kameez");
        container5.setImage("https://pngimage.net/wp-content/uploads/2018/06/kurta-png-6.png");

        list.add(container);
        list.add(container1);
        list.add(container2);
        list.add(container3);
        list.add(container4);
        list.add(container5);

       Clothing_Gird_View adapter=new Clothing_Gird_View(this,list);
       girdview.setExpanded(true);
        adapter.notifyDataSetChanged();
       girdview.setAdapter(adapter);

    }


}

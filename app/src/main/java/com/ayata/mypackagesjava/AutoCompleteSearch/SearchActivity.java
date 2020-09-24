package com.ayata.mypackagesjava.AutoCompleteSearch;

import android.app.SearchManager;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.CursorAdapter;
import android.widget.SearchView;
import android.widget.SimpleCursorAdapter;

import com.ayata.mypackagesjava.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

/*searchview has its autocomplete textview
if you are doing this in fragments then make sure yoou have toolbar in activity
searchables xml needed
menu_search needed
meta data and intent search needed on Manifest file for that search activity
 */
public class SearchActivity extends AppCompatActivity {
    AutoCompleteTextView mSearchAutoCompleteTextView;
    private static final String[] SUGGESTIONS = {
            "Bauru", "Sao Paulo", "Rio de Janeiro",
            "Bahia", "Mato Grosso", "Minas Gerais",
            "Tocantins", "Rio Grande do Sul"
    };
    private SimpleCursorAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        final String[] from = new String[]{"cityName"};
        final int[] to = new int[]{R.id.dropdown_text};
        mAdapter = new SimpleCursorAdapter(getApplicationContext(),
                R.layout.layout_search_dropdown,
                null,
                from,
                to,
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(SearchActivity.this.getComponentName()));


        //if true then we need to click again to search after going to this activity
        searchView.setIconified(false);
        searchView.setSuggestionsAdapter(mAdapter);

        int autoCompleteTextViewID = getResources().getIdentifier("android:id/search_src_text", "id", getPackageName());
        mSearchAutoCompleteTextView = searchView.findViewById(autoCompleteTextViewID);
        mSearchAutoCompleteTextView.setDropDownBackgroundResource(R.color.colorTransparent);// Setting background color of the suggestion drop down list
        mSearchAutoCompleteTextView.setHint("Search");
        mSearchAutoCompleteTextView.setHintTextColor(Color.parseColor("#cccccc"));

//if we want to put suggestion dialog to appear in middle without shift
//        final View dropDownAnchor = searchView.findViewById(mSearchAutoCompleteTextView.getDropDownAnchor());
//        if (dropDownAnchor != null) {
//            dropDownAnchor.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
//                @Override
//                public void onLayoutChange(View v, int left, int top, int right, int bottom,
//                                           int oldLeft, int oldTop, int oldRight, int oldBottom) {
//
//                    // calculate width of DropdownView
//
//
//                    int point[] = new int[2];
//                    dropDownAnchor.getLocationOnScreen(point);
//                    // x coordinate of DropDownView
//                    int dropDownPadding = point[0] + mSearchAutoCompleteTextView.getDropDownHorizontalOffset();
//
//                    Rect screenSize = new Rect();
//                    getWindowManager().getDefaultDisplay().getRectSize(screenSize);
//                    // screen width
//                    int screenWidth = screenSize.width();
//
//                    // set DropDownView width
//                    mSearchAutoCompleteTextView.setDropDownWidth(screenWidth - dropDownPadding * 2);
//                }
//            });
//        }


        // Getting selected (clicked) item suggestion
        searchView.setOnSuggestionListener(new SearchView.OnSuggestionListener() {
            @Override
            public boolean onSuggestionClick(int position) {
                Cursor cursor = (Cursor) mAdapter.getItem(position);
                String txt = cursor.getString(cursor.getColumnIndex("cityName"));
                searchView.setQuery(txt, true);
                return true;
            }

            @Override
            public boolean onSuggestionSelect(int position) {
                // Your code here
                return true;
            }
        });

        //this section is for no popupelevation ie by setting transparent
        int searchPlateId = searchView.getContext().getResources().getIdentifier("android:id/search_plate", null, null);
        View searchPlate = searchView.findViewById(searchPlateId);
        if (searchPlate != null) {
            searchPlate.setBackgroundColor(Color.TRANSPARENT);
//            int searchTextId = searchPlate.getContext ().getResources ().getIdentifier ("android:id/search_src_text", null, null);

        }


        //query listener
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                finish();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mSearchAutoCompleteTextView.setDropDownWidth(getResources().getDisplayMetrics().widthPixels);
                populateAdapter(newText);
                return false;
            }
        });
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                finish();
                return false;
            }
        });
        return true;
    }

    /*this section is such that back arrow on toolbar goes back to fragment calling it
    if it was activity e could set parent activity but since the caller is fragment, we can set as code below
    here return true means: "dont do anything now by system because i have handled already"               */
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    // You must implements your logic to get data using OrmLite
    private void populateAdapter(String query) {
        final MatrixCursor c = new MatrixCursor(new String[]{BaseColumns._ID, "cityName"});
        for (int i = 0; i < SUGGESTIONS.length; i++) {
            if (SUGGESTIONS[i].toLowerCase().startsWith(query.toLowerCase()))
                c.addRow(new Object[]{i, SUGGESTIONS[i]});
        }
        mAdapter.changeCursor(c);
        mAdapter.notifyDataSetChanged();
    }
}
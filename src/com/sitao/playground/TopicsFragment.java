package com.sitao.playground;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TopicsFragment extends Fragment implements ActionBar.TabListener {

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a {@link FragmentPagerAdapter}
	 * derivative, which will keep every loaded fragment in memory. If this
	 * becomes too memory intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;

	private LayoutInflater inflater;
	private ViewGroup container;
	private View rootView;
	private LandingActivity activity;
	private FragmentTabHost tabHost;

	public TopicsFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		this.inflater = inflater;
		this.container = container;
		this.rootView = inflater.inflate(R.layout.fragment_topics, container,
				false);
		this.activity = (LandingActivity) getActivity();
		
		// Setup tab list
		List<String> tabNames = new ArrayList<String>();
		tabNames.add("All");
		tabNames.add("New");
		tabNames.add("Sug.");
		tabNames.add("Pop.");
		tabNames.add("Fav.");

		// Setup the FragmentTabHost
		this.tabHost = new FragmentTabHost(this.activity);
		this.tabHost.setup(this.activity, getChildFragmentManager(),
				container.getId());

		// Create tabs
		Bundle arg;
		for (int i = 0; i < tabNames.size(); i++) {
			arg = new Bundle();
			arg.putInt(PlaceholderFragment.ARG_SECTION_NUMBER, i + 1);
			tabHost.addTab(
					tabHost.newTabSpec("Tab" + i).setIndicator(tabNames.get(i)),
					PlaceholderFragment.class, arg);
		}

/*		// Set up the action bar.
		final ActionBar actionBar = this.activity.getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the activity.
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getChildFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) this.tabHost.findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

		// When swiping between different sections, select the corresponding
		// tab. We can also use ActionBar.Tab#select() to do this if we have
		// a reference to the Tab.
		mViewPager
				.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						actionBar.setSelectedNavigationItem(position);
					}
				});

		// For each of the sections in the app, add a tab to the action bar.
		for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
			// Create a tab with text corresponding to the page title defined by
			// the adapter. Also specify this Activity object, which implements
			// the TabListener interface, as the callback (listener) for when
			// this tab is selected.
			actionBar.addTab(actionBar.newTab()
					.setText(mSectionsPagerAdapter.getPageTitle(i))
					.setTabListener(this));
		}*/

		return this.tabHost;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		((LandingActivity) activity).onSectionAttached(LandingActivity.TOPICS);
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

	@Override
	public void onTabSelected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
		// When the given tab is selected, switch to the corresponding page in
		// the ViewPager.
		mViewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a PlaceholderFragment (defined as a static inner class
			// below).
			return PlaceholderFragment.newInstance(position + 1);
		}

		@Override
		public int getCount() {
			// Show 5 total pages.
			return 5;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return getString(R.string.topic_section1).toUpperCase(l);
			case 1:
				return getString(R.string.topic_section2).toUpperCase(l);
			case 2:
				return getString(R.string.topic_section3).toUpperCase(l);
			case 3:
				return getString(R.string.topic_section4).toUpperCase(l);
			case 4:
				return getString(R.string.topic_section5).toUpperCase(l);

			}
			return null;
		}
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		private static final String ARG_SECTION_NUMBER = "section_number";

		/**
		 * Returns a new instance of this fragment for the given section number.
		 */
		public static PlaceholderFragment newInstance(int sectionNumber) {
			PlaceholderFragment fragment = new PlaceholderFragment();
			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
			fragment.setArguments(args);
			return fragment;
		}

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			int num = getArguments().getInt(ARG_SECTION_NUMBER);
			View rootView = null;
			TextView tv;
			switch (num) {
			case 1:
				rootView = inflater.inflate(R.layout.fragment_topics_all,
						container, false);
				break;
			case 2:
				rootView = inflater.inflate(R.layout.fragment_topics_default,
						container, false);
				tv = (TextView) rootView.findViewById(R.id.section_label);
				tv.setText("Section " + num);
				break;
			case 3:
				rootView = inflater.inflate(R.layout.fragment_topics_default,
						container, false);
				tv = (TextView) rootView.findViewById(R.id.section_label);
				tv.setText("Section " + num);
				break;
			case 4:
				rootView = inflater.inflate(R.layout.fragment_topics_default,
						container, false);
				tv = (TextView) rootView.findViewById(R.id.section_label);
				tv.setText("Section " + num);
				break;
			case 5:
				rootView = inflater.inflate(R.layout.fragment_topics_default,
						container, false);
				tv = (TextView) rootView.findViewById(R.id.section_label);
				tv.setText("Section " + num);
				break;
			}

			return rootView;
		}
	}

}

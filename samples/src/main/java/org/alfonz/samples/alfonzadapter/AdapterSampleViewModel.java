package org.alfonz.samples.alfonzadapter;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import org.alfonz.samples.alfonzarch.BaseViewModel;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class AdapterSampleViewModel extends BaseViewModel implements LifecycleObserver
{
	public final ObservableList<String> messages = new ObservableArrayList<>();
	public final ObservableList<Integer> numbers = new ObservableArrayList<>();
	public final ObservableList<Boolean> bits = new ObservableArrayList<>();

	private int mCounter = 0;


	@OnLifecycleEvent(Lifecycle.Event.ON_START)
	public void onStart()
	{
		// load data
		if(messages.isEmpty()) loadData();
	}


	public String addMessage()
	{
		String[] months = new DateFormatSymbols().getMonths();
		String message = createMessage(months[mCounter % 12]);
		messages.add(message);
		return message;
	}


	public void removeMessage(String message)
	{
		messages.remove(message);
	}


	private void loadData()
	{
		loadMessages();
		loadNumbers();
		loadBits();
	}


	private void loadMessages()
	{
		String[] months = new DateFormatSymbols().getMonths();
		List<String> list = new ArrayList<>();
		for(int i = 0; i < months.length; i++)
		{
			list.add(createMessage(months[i]));
		}
		messages.addAll(list);
	}


	private void loadNumbers()
	{
		int a = 0;
		int b = 1;

		List<Integer> list = new ArrayList<>();
		for(int i = 0; i < 16; i++)
		{
			list.add(a);
			a = a + b;
			b = a - b;
		}
		numbers.addAll(list);
	}


	private void loadBits()
	{
		List<Boolean> list = new ArrayList<>();
		for(int i = 0; i < 6; i++)
		{
			list.add(i % 2 == 0);
		}
		bits.addAll(list);
	}


	private String createMessage(String month)
	{
		return String.format(Locale.US, "%03d %s", ++mCounter, month);
	}
}

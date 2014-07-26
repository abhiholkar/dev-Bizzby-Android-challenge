package com.bizzbyandroidchallenge.adapter;

import java.util.List;

import com.bizzbyandroidchallenge.data.model.CurrencyCode;
import com.bizzbyandroidchallenge.view.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * @author Abhi
 *Adapter for view model
 *This class extends Base Adapter and 
 * overides the getView method
 *
 */
public class CurrencyCodeAdapter extends BaseAdapter {

	/**List of CurrencyCodes */
	private List<CurrencyCode> codeList;
	
	/**Context */
	private Context context;

	public CurrencyCodeAdapter(Context context, List<CurrencyCode> codeList) {
		super();
		this.context = context;
		this.codeList = codeList;
	}

	@Override
	public int getCount() {

		return codeList.size();
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getItem(int)
	 */
	@Override
	public CurrencyCode getItem(int position) {
		
		return (CurrencyCode) codeList.get(position);
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getItemId(int)
	 */
	@Override
	public long getItemId(int position) {

		return 0;
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	/* (non-Javadoc)
	 * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	/* (non-Javadoc)
	 * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
	 * In this method we Tag the view with a View Holder to avoid 
	 * inflation of view everytime. This saves CPU cycles and makes list smooth
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder holder;

		if (convertView == null) {

			convertView = View.inflate(context, R.layout.currency_code_row,
					null);

			holder = new ViewHolder();

			holder.shortCode = (TextView) convertView
					.findViewById(R.id.shortcode);
			holder.fullCode = (TextView) convertView
					.findViewById(R.id.fullcode);

			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();

		}

		CurrencyCode currencyCode = codeList.get(position);
		holder.shortCode.setText(currencyCode.getCurrencycodeSF());
		holder.fullCode.setText(currencyCode.getCurrencyCodeLF());
		return convertView;
	}

	/**
	 * @author Abhi
	 * View Holder for tagging the view
	 */
	private class ViewHolder {

		TextView shortCode;
		TextView fullCode;
	}

}

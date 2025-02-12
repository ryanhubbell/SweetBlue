package com.idevicesinc.sweetblue.toolbox.view;

import android.content.Context;
import android.content.Intent;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.idevicesinc.sweetblue.BleDevice;
import com.idevicesinc.sweetblue.toolbox.activity.DeviceDetailsActivity;

import java.util.List;


public class ScanAdapter extends RecyclerView.Adapter<ScanAdapter.DeviceHolder>
{

    private List<BleDevice> m_deviceList;
    private Context m_context;


    public ScanAdapter(Context context, List<BleDevice> deviceList)
    {
        m_deviceList = deviceList;
        m_context = context;
    }

    @Override public DeviceHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        DeviceRow row = new DeviceRow(m_context);
        DeviceHolder holder = new DeviceHolder(row);
        return holder;
    }

    @Override public void onBindViewHolder(final DeviceHolder holder, int position)
    {
        BleDevice device = m_deviceList.get(position);

        holder.setDevice(device);

        holder.row.setOnClickListener(v ->
        {
            if (holder.row.hasDevice())
            {
                Intent intent = new Intent(m_context, DeviceDetailsActivity.class);
                intent.putExtra("mac", holder.row.macAddress());
                m_context.startActivity(intent);
            }
        });
    }

    @Override public int getItemCount()
    {
        return m_deviceList.size();
    }

    @Override public void onViewRecycled(DeviceHolder holder)
    {
        holder.clearDevice();
        super.onViewRecycled(holder);
    }

    static class DeviceHolder extends RecyclerView.ViewHolder
    {

        private final DeviceRow row;

        public DeviceHolder(DeviceRow view)
        {
            super(view);
            row = view;
        }

        public void clearDevice()
        {
            row.clearDevice();
        }

        public void setDevice(BleDevice device)
        {
            row.setBleDevice(device);
        }
    }
}

package com.belatrix.kotlintemplate.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.belatrix.kotlintemplate.R;
import com.belatrix.kotlintemplate.fragments.listener.OnNoteListener;
import com.belatrix.kotlintemplate.model.NoteDbEntity;


public class DetailsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private Button btnDeleteNote;
    private Button btnEditNote;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnNoteListener mListener;
    private NoteDbEntity noteDbEntity;

    private  String editNoteName, editNoteDesc;

    // TODO: Rename and change types and number of parameters
    public static DetailsFragment newInstance(String param1, String param2) {
        DetailsFragment fragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public DetailsFragment() {
        // Required empty public constructor
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false);
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnNoteListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        btnDeleteNote=(Button)getView().findViewById(R.id.btnDeleteNote);
        btnEditNote=(Button)getView().findViewById(R.id.btnEditNote);

        if(getArguments()!=null)
        {
            noteDbEntity = (NoteDbEntity)getArguments().getSerializable("NOTE");
        }
        if(noteDbEntity !=null)
        {
            //TODO mostrar INFO
            String name= noteDbEntity.getName().toString();
            String desc= noteDbEntity.getDescription().toString();

            ((EditText)getView().findViewById(R.id.eteName)).setText(name);
            ((EditText)getView().findViewById(R.id.eteDesc)).setText(desc);
        }

        btnDeleteNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.deleteNote(noteDbEntity);
            }
        });
        btnEditNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateForm()){
                    editNote();
                    closeActivity();
                }
            }
        });
    }

    private void editNote(){
        //base de datos
        int id = noteDbEntity.getId();
        NoteDbEntity editNoteDbEntity = new NoteDbEntity(id,editNoteName,editNoteDesc,null);
        mListener.editNote(editNoteDbEntity);
    }

    private boolean validateForm(){
        //ui
        editNoteName=   ((EditText)getView().findViewById(R.id.eteName)).getText().toString();
        editNoteDesc= ((EditText)getView().findViewById(R.id.eteDesc)).getText().toString();

        if(editNoteName.isEmpty()){
            return false;
        }
        if(editNoteDesc.isEmpty()){
            return false;
        }
        return true;
    }

    private void closeActivity(){
        getActivity().finish();
    }
}

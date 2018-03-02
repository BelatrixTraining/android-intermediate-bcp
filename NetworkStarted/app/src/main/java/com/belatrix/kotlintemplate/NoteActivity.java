package com.belatrix.kotlintemplate;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.belatrix.kotlintemplate.fragments.AddNoteFragment;
import com.belatrix.kotlintemplate.fragments.DetailsFragment;
import com.belatrix.kotlintemplate.fragments.listener.OnNoteListener;
import com.belatrix.kotlintemplate.model.NoteDbEntity;
import com.belatrix.kotlintemplate.storage.CRUDOperations;
import com.belatrix.kotlintemplate.storage.MyDatabase;
import com.belatrix.kotlintemplate.storage.NoteRepository;
import com.belatrix.kotlintemplate.ui.dialogs.MyDialogFragment;
import com.belatrix.kotlintemplate.ui.dialogs.MyDialogListener;


public class NoteActivity extends BaseActivity implements OnNoteListener, MyDialogListener {

    public static final  int ADD_NOTE=100;
    public static final  int DETAIL_NOTE=101;
    public static final  int UPDATE_NOTE=102;

    private static final String TAG ="NoteActivity";

    private AddNoteFragment addNoteFragment= AddNoteFragment.newInstance(null,null);
    private DetailsFragment detailsFragment= DetailsFragment.newInstance(null,null);
    private int fragmentSelected= DETAIL_NOTE;
    private NoteDbEntity noteDbEntity;
    private NoteDbEntity currentNote;


    private CRUDOperations crudOperations;
    private NoteRepository noteRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        validateExtras();
        enabledBack();
        crudOperations= new CRUDOperations(new MyDatabase(this));
        noteRepository= ((NoteApplication)(getApplication())).getNoteRepository();

        Bundle bundle= new Bundle();
        bundle.putSerializable("NOTE", noteDbEntity);
        changeFragment(fragmentSelected, bundle);
    }

    private void validateExtras() {
        if(getIntent().getExtras()!=null)
        {
            fragmentSelected= getIntent().getExtras().getInt("FRAGMENT",DETAIL_NOTE);
            noteDbEntity = (NoteDbEntity)getIntent().getExtras().getSerializable("NOTE");
        }
    }


    private  void changeFragment(int id,Bundle bundle)
    {
        Fragment fragment= null;
        switch (id)
        {
            case ADD_NOTE:
                fragment=addNoteFragment;
                break;

            case DETAIL_NOTE:
                fragment=detailsFragment;
                break;

            case UPDATE_NOTE:
                fragment=null;
                break;
        }

        if(fragment!=null)
        {
            fragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit();
        }
    }

    @Override
    public CRUDOperations getCrudOperations() {
        return crudOperations;
    }

    @Override
    public NoteRepository getNoteRepository() {
        return noteRepository;
    }

    @Override
    public void deleteNote(NoteDbEntity noteDbEntity) {

        currentNote= noteDbEntity;
        MyDialogFragment myDialogFragment =new MyDialogFragment();
        Bundle bundle= new Bundle();
        bundle.putString("TITLE","¿Deseas eliminar esta nota?");
        bundle.putInt("TYPE",100);

        myDialogFragment.setArguments(bundle);
        myDialogFragment.show(getSupportFragmentManager(), "dialog");
    }

    @Override
    public void editNote(NoteDbEntity noteDbEntity) {
        crudOperations.updateNote(noteDbEntity);
    }

    @Override
    public void onPositiveListener(Object object, int type) {
        Log.v("CONSOLE", "dialog positive");
        if(currentNote!=null) {
            crudOperations.deleteNote(currentNote);
            currentNote=null;
            finish();
        }
    }

    @Override
    public void onNegativeListener(Object object, int type) {
        Log.v(TAG, "dialog negative");
    }
}

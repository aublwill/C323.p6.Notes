Collaboration with Kuzey Bektas

# C323.p7.Notes
This project functions as a notes app.

## Functionality 
The following **required** functionality is completed:
* [] user sees a screen with (maybe) notes on it or a sign-in/sign-up screen
* [] if user is not signed in, they can either sign in using email or password or sign up with email and password
* [] once signed in, user can see their existing notes
* [] on this screen, user can add note(note with '+' button), or sign-out(person button) within the top toolbar
* [] user can see each note title along with their contents
* [] user can either press on an existing note to edit/view it
* [] user can press save to save the note and naviagte back to home screen
* [] user can press the red "X" within a note for a confirmation to pop up
* [] if user presses "no", nothing happens, if user presses "yes", the selected note will be deleted
* [] each user has their own private set of notes

The folowing **extensions** are implemented:

* import android.app.AlertDialog
* import android.app.Dialog
* import android.content.Context
* import android.os.Bundle
* import android.util.Log
* import androidx.fragment.app.DialogFragment
* import android.os.Bundle
* import android.view.LayoutInflater
* import android.view.View
* import android.view.ViewGroup
* import androidx.fragment.app.Fragment
* import androidx.lifecycle.Observer
* import androidx.lifecycle.ViewModelProvider
* import androidx.navigation.findNavController
* import com.example.c323p6notes.databinding.FragmentEditNoteBinding
* import androidx.lifecycle.LiveData
* import androidx.lifecycle.MutableLiveData
* import androidx.lifecycle.ViewModel
* import androidx.lifecycle.viewModelScope
* import kotlinx.coroutines.launch
* import java.lang.IllegalArgumentException
* import androidx.appcompat.app.AppCompatActivity
* import androidx.room.ColumnInfo
* import androidx.recyclerview.widget.DiffUtil
* import androidx.recyclerview.widget.ListAdapter
* import androidx.recyclerview.widget.RecyclerView
* import com.example.c323p6notes.databinding.NoteItemBinding
* import com.google.firebase.auth.FirebaseAuth
* import com.google.firebase.auth.FirebaseUser
* import com.google.firebase.auth.ktx.auth
* import com.google.firebase.database.DataSnapshot
* import com.google.firebase.database.DatabaseError
* import com.google.firebase.database.DatabaseReference
* import com.google.firebase.database.ValueEventListener
* import com.google.firebase.database.ktx.getValue
* import com.google.firebase.database.ktx.database
* import com.google.firebase.ktx.Firebase
* import com.google.firebase.database.Exclude
  
## Video Walkthrough 




https://github.com/aublwill/C323.p6.Notes/assets/143005409/2ed6a119-cfcd-41f7-bd95-e115b0e6448f




## Notes
* Should work as intended

## License
Copyright [2023] [Aubrey Williams]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

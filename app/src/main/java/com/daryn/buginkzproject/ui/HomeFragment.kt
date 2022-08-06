package com.daryn.buginkzproject.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.daryn.buginkzproject.NewsApi
import com.daryn.buginkzproject.R
import com.daryn.buginkzproject.RetrofitHelper

import com.daryn.buginkzproject.adapters.AuthorListAdapter
import com.daryn.buginkzproject.adapters.PostListAdapter
import com.daryn.buginkzproject.databinding.FragmentHomeBinding
import com.daryn.buginkzproject.models.AuthorViewModel
import com.daryn.buginkzproject.models.Datum
import com.daryn.buginkzproject.models.HomeViewModel
import com.daryn.buginkzproject.models.PostViewModel
import com.google.gson.GsonBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    var listofNews=ArrayList<Datum>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val service = RetrofitHelper.getInstance().create(NewsApi::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            val response = service.getNews()
            withContext(Dispatchers.Main) {
                Log.d("JSONd --------------- :", response.body().toString())
                val datafromApi=response.body()
                if (datafromApi != null) {
                    listofNews= datafromApi.data
                }

                if (response.isSuccessful) {

                    val gson = GsonBuilder().setPrettyPrinting().create()
                    val dataJson = gson.toJson(
                        response.body()
                    )

                        Log.d("JSON :", dataJson)

                } else {

                    Log.e("RETROFIT_ERROR", response.code().toString())

                }
            }
        }
        setupRecyclerView()
        setupPostsRecyclerView()
        return root
    }
    private fun setupRecyclerView() {
        binding.recyclerview.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = AuthorListAdapter(createAuthorList()) { author, position ->
                Toast.makeText(
                    requireActivity(),
                    "Clisicked on actor: ${author.name}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
    private fun setupPostsRecyclerView() {
        binding.recyclerViewPosts.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = PostListAdapter(createPostList(listofNews))
            }
        }

    private fun createAuthorList(): ArrayList<AuthorViewModel> {
        return arrayListOf<AuthorViewModel>(
            AuthorViewModel(
                "Author1",
                R.drawable.author1
            ),
            AuthorViewModel(
                "Author2",
                R.drawable.author2
            ),
            AuthorViewModel(
                "Author3",
                R.drawable.author3
            ),
            AuthorViewModel(
                "Author4",
            R.drawable.author4
            ),
            AuthorViewModel(
                "Author5",
                R.drawable.author5
            )

        )
    }
    private fun createPostList(news:ArrayList<Datum>): ArrayList<PostViewModel> {
        val arrayList=ArrayList<PostViewModel>()
        for(i in news.indices){

            news[i].newsName?.let { PostViewModel(R.drawable.post1, it) }?.let { arrayList.add(it) }
        }
        return arrayList
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
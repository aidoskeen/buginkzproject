package com.daryn.buginkzproject.ui

import android.os.Bundle
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
import com.daryn.buginkzproject.models.*
import retrofit2.*


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel =
            ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        getData()
        setupRecyclerView()

        return root
    }

    private fun getData() {
        val retrofitBuilder = RetrofitHelper.getInstance().create(NewsApi::class.java)
        val jsonResponse = retrofitBuilder.getNews()
        jsonResponse.enqueue(object :Callback<NewsList>{
            override fun onResponse(call: Call<NewsList>, response: Response<NewsList>) {
                val newslist = response.body()
                val dataList = newslist?.data?.let { ArrayList(it) }
                if (dataList != null) {
                    setupPostsRecyclerView(dataList)
                }
            }

            override fun onFailure(call: Call<NewsList>, t: Throwable) {
                Toast.makeText(requireContext(),"FAILURE",Toast.LENGTH_SHORT).show()
            }
        })
    }
    private fun setupRecyclerView() {
        binding.recyclerview.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = AuthorListAdapter(createAuthorList()) { author, position ->
                Toast.makeText(
                    requireActivity(),
                    "Clicked on actor: ${author.name}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
    private fun setupPostsRecyclerView(listofNews:List<Datum>) {
        binding.recyclerViewPosts.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = PostListAdapter(listofNews)
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}





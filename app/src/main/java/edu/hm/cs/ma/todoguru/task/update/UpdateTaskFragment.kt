package edu.hm.cs.ma.todoguru.task.update

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import edu.hm.cs.ma.todoguru.R
import edu.hm.cs.ma.todoguru.databinding.UpdateTaskFragmentBinding
import edu.hm.cs.ma.todoguru.task.TaskFragment

class UpdateTaskFragment : TaskFragment() {

    private lateinit var binding: UpdateTaskFragmentBinding
    private val args: UpdateTaskFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.update_task_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            viewModel = this@UpdateTaskFragment.viewModel
            lifecycleOwner = this@UpdateTaskFragment
        }

        viewModel.setDefaultUpdateValue(args.task)

        binding.apply {
            dueDate.setOnClickListener { openSetDueDateDialog(this@UpdateTaskFragment.viewModel.dueDate.value!!) }
            chipSetReminder.setOnClickListener {
                updateValues()
                openSetReminderDialog()
            }
            chipSetCategory.setOnClickListener {
                updateValues()
                openSetCategoryDialog()
            }
            chipSetSubTask.setOnClickListener {
                updateValues()
                openSetSubTaskDialog()
            }
            chipSetRepeat.setOnClickListener {
                updateValues()
                openSetRepeatDialog()
            }

            switchButton.setOnCheckedChangeListener { _, _ ->
                updateValues()
            }

            updateTaskButton.setOnClickListener {
                if (validateUserInput()) {
                    updateValues()
                    this@UpdateTaskFragment.viewModel.updateTask()
                    findNavController().popBackStack()
                }
            }
        }
    }

    override fun openSetReminderDialog() {
        findNavController().navigate(UpdateTaskFragmentDirections.actionUpdateTaskFragmentToSetReminderFragment())
    }

    private fun openSetCategoryDialog() {
        findNavController().navigate(UpdateTaskFragmentDirections.actionUpdateTaskFragmentToSetCategoryDialog())
    }

    private fun openSetSubTaskDialog() {
        findNavController().navigate(UpdateTaskFragmentDirections.actionUpdateTaskFragmentToSubTaskListFragment())
    }

    private fun openSetRepeatDialog() {
        findNavController().navigate(UpdateTaskFragmentDirections.actionUpdateTaskFragmentToSetRepeatDialog())
    }

    override fun getTitle() = binding.title

    override fun getDescription() = binding.description

    override fun getEstimated() = binding.estimated

    override fun getPriority() = binding.switchButton
}
package edu.hm.cs.ma.todoguru.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.io.Serializable
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.Period

@Entity(tableName = "task_table")
@Parcelize
data class Task(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "description")
    var description: String,
    @ColumnInfo(name = "due Date")
    var dueDate: LocalDate,
    @ColumnInfo(name = "estimated Time")
    var estimated: Int,
    @ColumnInfo(name = "reminder")
    var reminder: LocalDateTime,
    @ColumnInfo(name = "done")
    var done: Boolean,
    @ColumnInfo(name = "category")
    var category: String?,
    @ColumnInfo(name = "sub_task")
    var subTask: List<SubTask>,
    @ColumnInfo(name = "duration")
    var repeat: Period?,
    @ColumnInfo(name = "priority")
    var priority: Boolean
) : Serializable, Parcelable {

    constructor(
        task: Task
    ) : this(
        task.title,
        task.description,
        task.dueDate,
        task.estimated,
        task.reminder,
        task.category,
        task.subTask,
        task.repeat,
        task.priority
    )

    constructor(
        id: Long,
        title: String,
        description: String,
        dueDate: LocalDate,
        estimated: Int,
        reminder: LocalDateTime,
        category: String?,
        subTask: List<SubTask>,
        repeat: Period?,
        priority: Boolean
    ) : this(id, title, description, dueDate, estimated, reminder, false, category, subTask, repeat, priority)

    constructor(
        title: String,
        description: String,
        dueDate: LocalDate,
        estimated: Int,
        reminder: LocalDateTime,
        category: String?,
        subTask: List<SubTask>,
        repeat: Period?,
        priority: Boolean
    ) : this(0, title, description, dueDate, estimated, reminder, false, category, subTask, repeat, priority)
}

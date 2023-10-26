package com.tisto.simpleapp.core.source.local.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tisto.simpleapp.util.randomColor
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var name: String? = null,
    var email: String? = null,
    val password: String? = null,
    val color: Int = randomColor(),
    val age: Int = 0
) : Parcelable
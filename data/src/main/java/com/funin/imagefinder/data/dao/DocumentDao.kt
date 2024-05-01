package com.funin.imagefinder.data.dao

import androidx.room.Dao
import com.funin.imagefinder.domain.entity.Document

@Dao
abstract class DocumentDao : BaseDao<Document>()
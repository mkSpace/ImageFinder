package com.funin.imagefinder.data.datasource.local.dao

import androidx.room.Dao
import com.funin.imagefinder.data.datasource.local.entity.Document

@Dao
abstract class DocumentDao : BaseDao<Document>()
package com.git.admin.data.model.base

import com.git.admin.domain.model.BaseModel

interface BaseEntity {
    fun toModel(): BaseModel
}
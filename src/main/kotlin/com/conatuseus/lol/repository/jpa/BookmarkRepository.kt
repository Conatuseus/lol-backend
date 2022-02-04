package com.conatuseus.lol.repository.jpa

import com.conatuseus.lol.model.entity.Bookmark
import org.springframework.data.jpa.repository.JpaRepository

interface BookmarkRepository : JpaRepository<Bookmark, Long>

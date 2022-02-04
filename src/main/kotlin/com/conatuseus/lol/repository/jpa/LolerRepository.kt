package com.conatuseus.lol.repository.jpa

import com.conatuseus.lol.model.entity.Loler
import org.springframework.data.jpa.repository.JpaRepository

interface LolerRepository : JpaRepository<Loler, Long>

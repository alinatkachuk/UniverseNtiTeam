package com.alinatkachuk.universeNtiTeam.service;

import com.alinatkachuk.universeNtiTeam.model.Lord;

public interface LordService {

    Lord saveLord (Lord lord);

    Lord editLord (Long lordId, Lord lord);

}

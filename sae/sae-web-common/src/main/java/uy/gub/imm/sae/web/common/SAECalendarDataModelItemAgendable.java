/*
 * SAME - Sistema de Gestion de Turnos por Internet
 * SAME is a fork of SAE - Sistema de Agenda Electronica
 * 
 * Copyright (C) 2009  IMM - Intendencia Municipal de Montevideo
 * Copyright (C) 2013, 2014  SAGANT - Codestra S.R.L.
 * Copyright (C) 2013, 2014  Alvaro Rettich <alvaro@sagant.com>
 * Copyright (C) 2013, 2014  Carlos Gutierrez <carlos@sagant.com>
 * Copyright (C) 2013, 2014  Victor Dumas <victor@sagant.com>
 *
 * This file is part of SAME.

 * SAME is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package uy.gub.imm.sae.web.common;

import org.richfaces.model.CalendarDataModelItem;

public class SAECalendarDataModelItemAgendable 
		extends SAECalendarDataModelItem
		implements CalendarDataModelItem {

	public static final String CSS_DIA_SIN_CUPO    = "diaSinCupo";
	public static final String CSS_DIA_CON_CUPO    = "diaConCupo";
	
	//Cantidad de cupos libres para la fecha de este item.
	private int cupos;


	//Crea un item agendable, es decir, la fecha de este item es una fecha valida para la agenda.
	public SAECalendarDataModelItemAgendable(int cupos) {

		super();
		
		if (cupos < 0) {
			//TODO EXCEPCIONES
			throw new RuntimeException("Creando un item agendable con cupo negativo");
		}
		
		this.cupos = cupos;
	}

	@Override
	public String getStyleClass() {
		String styleClass;
		if (cupos > 0){
			styleClass = CSS_DIA_CON_CUPO;
		}
		else {
			styleClass = CSS_DIA_SIN_CUPO;
		}
		
		return styleClass;
	}

	@Override
	public boolean isEnabled() {
		return (cupos > 0);
	}

	@Override
	public Object getData() {
		return "C.: "+cupos;
	}
}

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

package uy.gub.imm.sae.web.timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import uy.gub.imm.sae.business.api.DepurarReservas;
import uy.gub.imm.sae.common.exception.ApplicationException;
import uy.gub.imm.sae.common.factories.BusinessLocatorFactory;

public class StartupContextListener implements ServletContextListener {

	static Logger logger = Logger.getLogger(StartupContextListener.class);
	
	public void contextInitialized(ServletContextEvent arg0) {
	
		try {
			DepurarReservas mantenerReservasEJB = BusinessLocatorFactory.getLocatorContextoNoAutenticado().getDepurarReservas();
				
			if(mantenerReservasEJB==null)
				logger.info("No se pudo obtener la instancia del bean para eliminar Reservas Pendientes");
			else {
				logger.info("Obtuve la instancia del bean de para eliminar Reservas Pendientes");
				mantenerReservasEJB.initTimers();
			}
			
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
		
		logger.info("Finaliza inicializacion de timer para eliminar Reservas Pendientes");
	}

	public void contextDestroyed(ServletContextEvent arg0) {

	}

}

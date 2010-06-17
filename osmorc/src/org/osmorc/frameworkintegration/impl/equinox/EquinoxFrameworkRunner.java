/*
 * Copyright (c) 2007-2009, Osmorc Development Team
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright notice, this list
 *       of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright notice, this
 *       list of conditions and the following disclaimer in the documentation and/or other
 *       materials provided with the distribution.
 *     * Neither the name of 'Osmorc Development Team' nor the names of its contributors may be
 *       used to endorse or promote products derived from this software without specific
 *       prior written permission.
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL
 * THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT
 * OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR
 * TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.osmorc.frameworkintegration.impl.equinox;

import com.intellij.execution.configurations.ParametersList;
import org.jetbrains.annotations.NotNull;
import org.osmorc.frameworkintegration.impl.AbstractPaxBasedFrameworkRunner;
import org.osmorc.run.ui.SelectedBundle;

import java.util.Map;

/**
 * Framework runner for the Equinox OSGi framework.
 *
 * @author <a href="mailto:janthomae@janthomae.de">Jan Thom&auml;</a>
 * @author Robert F. Beeger (robert@beeger.net)
 * @version $Id$
 */
public class EquinoxFrameworkRunner extends AbstractPaxBasedFrameworkRunner<EquinoxRunProperties> {
  protected EquinoxFrameworkRunner() {
  }

  @NotNull
  @Override
  protected EquinoxRunProperties convertProperties(Map<String, String> properties) {
    return new EquinoxRunProperties(properties);


  }

  @NotNull
  @Override
  protected String getAdditionalTargetVMProperties(@NotNull SelectedBundle[] urlsOfBundlesToInstall) {
    StringBuilder result = new StringBuilder();
    String product = getFrameworkProperties().getEquinoxProduct();
    if (product != null && product.length() > 0) {
      result.append(" -Declipse.product=").append(product);
      result.append(" -Declipse.ignoreApp=").append("false");
    }
    else {
      String application = getFrameworkProperties().getEquinoxApplication();
      if (application != null && application.length() > 0) {
        result.append(" -Declipse.application=").append(application);
        result.append(" -Declipse.ignoreApp=").append("false");

      }
      else {
        result.append(" -Declipse.ignoreApp=").append("false");
      }
    }
    return result.toString();  
  }

  @NotNull
  @Override
  protected String getOsgiFrameworkName() {
    return "Equinox";
  }
}

package net.liftweb.mapper

/*
 * Copyright 2006-2008 WorldWide Conferencing, LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions
 * and limitations under the License.
 */

import scala.xml.{NodeSeq}
import net.liftweb.http.S
import net.liftweb.http.S._
import net.liftweb.util._

class MappedTextarea[T<:Mapper[T]](owner : T, maxLen: int) extends MappedString[T](owner, maxLen) {
  /**
   * Create an input field for the item
   */
  override def _toForm: Can[NodeSeq] = {
    val funcName = S.mapFunc({s: List[String] => this.setFromAny(s)})
    Full(<textarea name={funcName}
	 rows={textareaRows.toString}
	 cols={textareaCols.toString}>{is.toString}</textarea>)
  }

  override def toString = {
    val v = is
    if (v == null || v.length < 100) super.toString
    else {
      displayName +"="+v.substring(0,40)+" ... "+v.substring(v.length - 40)
    }
  }

  def textareaRows  = 8

  def textareaCols = 20

}

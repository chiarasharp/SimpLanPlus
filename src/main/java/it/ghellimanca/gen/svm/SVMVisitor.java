// Generated from /home/melania/Documents/UNI-BOH/1 ANNO/CI/progetto/SimpLanPlus/src/main/antlr/SVM.g4 by ANTLR 4.9.2

package it.ghellimanca.gen.svm;
import java.util.HashMap;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link SVMParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface SVMVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link SVMParser#assembly}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssembly(SVMParser.AssemblyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code push}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPush(SVMParser.PushContext ctx);
	/**
	 * Visit a parse tree produced by the {@code pop}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPop(SVMParser.PopContext ctx);
	/**
	 * Visit a parse tree produced by the {@code add}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdd(SVMParser.AddContext ctx);
	/**
	 * Visit a parse tree produced by the {@code addInt}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddInt(SVMParser.AddIntContext ctx);
	/**
	 * Visit a parse tree produced by the {@code sub}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSub(SVMParser.SubContext ctx);
	/**
	 * Visit a parse tree produced by the {@code subInt}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubInt(SVMParser.SubIntContext ctx);
	/**
	 * Visit a parse tree produced by the {@code mult}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMult(SVMParser.MultContext ctx);
	/**
	 * Visit a parse tree produced by the {@code multInt}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultInt(SVMParser.MultIntContext ctx);
	/**
	 * Visit a parse tree produced by the {@code div}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDiv(SVMParser.DivContext ctx);
	/**
	 * Visit a parse tree produced by the {@code divInt}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDivInt(SVMParser.DivIntContext ctx);
	/**
	 * Visit a parse tree produced by the {@code and}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnd(SVMParser.AndContext ctx);
	/**
	 * Visit a parse tree produced by the {@code or}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOr(SVMParser.OrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code not}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNot(SVMParser.NotContext ctx);
	/**
	 * Visit a parse tree produced by the {@code loadWord}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLoadWord(SVMParser.LoadWordContext ctx);
	/**
	 * Visit a parse tree produced by the {@code loadInteger}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLoadInteger(SVMParser.LoadIntegerContext ctx);
	/**
	 * Visit a parse tree produced by the {@code storeWord}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStoreWord(SVMParser.StoreWordContext ctx);
	/**
	 * Visit a parse tree produced by the {@code move}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMove(SVMParser.MoveContext ctx);
	/**
	 * Visit a parse tree produced by the {@code branchToLabel}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBranchToLabel(SVMParser.BranchToLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code branchIfEqual}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBranchIfEqual(SVMParser.BranchIfEqualContext ctx);
	/**
	 * Visit a parse tree produced by the {@code branchIfMoreEqual}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBranchIfMoreEqual(SVMParser.BranchIfMoreEqualContext ctx);
	/**
	 * Visit a parse tree produced by the {@code jumpAndSaveRA}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJumpAndSaveRA(SVMParser.JumpAndSaveRAContext ctx);
	/**
	 * Visit a parse tree produced by the {@code jumpToRegister}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJumpToRegister(SVMParser.JumpToRegisterContext ctx);
	/**
	 * Visit a parse tree produced by the {@code label}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLabel(SVMParser.LabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code halt}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHalt(SVMParser.HaltContext ctx);
	/**
	 * Visit a parse tree produced by the {@code print}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrint(SVMParser.PrintContext ctx);
}
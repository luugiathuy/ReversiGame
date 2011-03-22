//package com.luugiathuy.games.reversi;
//
//import java.awt.Graphics;
//import java.awt.Graphics2D;
//import java.awt.RenderingHints;
//import java.awt.geom.AffineTransform;
//import java.awt.image.BufferedImage;
//import java.util.ArrayList;
//
//public class PieceManager {
//	
//	private static PieceManager sInstance = null;
//	
//	private ArrayList<Piece> mPieceList;
//	
//	private PieceManager() {
//		mPieceList = new ArrayList<Piece>();
//	}
//	
//	public PieceManager getInstance() {
//		if (sInstance == null)
//			sInstance = new PieceManager();
//		
//		return sInstance;
//	}
//	
//	public void update(float elapsedTime) {
//		for (Piece p : mPieceList) {
//			p.update(elapsedTime);
//		}
//	}
//	
//	public void draw(Graphics g) {
//		for (Piece p : mPieceList) {
//			p.draw(g);
//		}
//	}
//	
//	////////////////////////////////////////////////////////////////////////
//	private class Piece {
//		// type of piece (black or white)
//		private char mPieceType;
//		
//		// Piece's position
//		private int mRow;
//		private int mCol;
//		
//		private boolean mIsFlipping;
//		private float mAnimationTime;
//		
//		private static final float sFLIPPING_DURATION = 1.0f;
//		
//		public Piece(char pieceType, int row, int col) {
//			mPieceType = pieceType;
//			mRow = row;
//			mCol = col;
//			
//			mIsFlipping = false;
//			mAnimationTime = 0.f;
//		}
//		
//		public void draw(Graphics g) {
//			if (!mIsFlipping) {
//				BufferedImage img = (mPieceType == Reversi.sBLACK_PIECE) ? Board.mBlackPieceImg : Board.mWhitePieceImg;
//				g.drawImage(img, 
//						(mCol * Board.sSQUARE_WIDTH) + Board.sSQUARE_OFFSET + Board.sBORDER_BOARD_OFFSET, 
//						(mRow * Board.sSQUARE_WIDTH) + Board.sSQUARE_OFFSET + Board.sBORDER_BOARD_OFFSET, null);
//			} else {
//				if ((mAnimationTime/sFLIPPING_DURATION) < 0.5f) {
//					BufferedImage img = (mPieceType == Reversi.sBLACK_PIECE) ? Board.mBlackPieceImg : Board.mWhitePieceImg;
//					int width = img.getWidth();
//					int height = img.getHeight();
//					BufferedImage scaledImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
//					Graphics2D graphics2D = scaledImage.createGraphics();
//					
//					double scale = (0.5f - mAnimationTime/sFLIPPING_DURATION) / 0.5f;
//					AffineTransform xform = AffineTransform.getScaleInstance(scale, 1.0);
//					graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
//					graphics2D.drawImage(img, xform, null);
//					graphics2D.dispose();
//					g.drawImage(scaledImage, 
//							(mCol * Board.sSQUARE_WIDTH) + Board.sSQUARE_OFFSET + Board.sBORDER_BOARD_OFFSET, 
//							(mRow * Board.sSQUARE_WIDTH) + Board.sSQUARE_OFFSET + Board.sBORDER_BOARD_OFFSET, null);
//				} else {
//					BufferedImage img = (mPieceType == Reversi.sBLACK_PIECE) ? Board.mWhitePieceImg : Board.mBlackPieceImg;
//					int width = img.getWidth();
//					int height = img.getHeight();
//					BufferedImage scaledImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
//					Graphics2D graphics2D = scaledImage.createGraphics();
//					
//					double scale = (mAnimationTime/sFLIPPING_DURATION - 0.5f) / 0.5f;
//					AffineTransform xform = AffineTransform.getScaleInstance(scale, 1.0);
//					graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
//					graphics2D.drawImage(img, xform, null);
//					graphics2D.dispose();
//					g.drawImage(scaledImage, 
//							(mCol * Board.sSQUARE_WIDTH) + Board.sSQUARE_OFFSET + Board.sBORDER_BOARD_OFFSET, 
//							(mRow * Board.sSQUARE_WIDTH) + Board.sSQUARE_OFFSET + Board.sBORDER_BOARD_OFFSET, null);
//				}
//			}
//		}
//		
//		public void update(float elapsedTime) {
//			if (mIsFlipping) {
//				mAnimationTime += elapsedTime;
//				
//				// check whether finish flipping
//				if (mAnimationTime > sFLIPPING_DURATION) {
//					// change piece's type
//					mPieceType = (mPieceType == Reversi.sBLACK_PIECE) ? Reversi.sWHITE_PIECE : Reversi.sBLACK_PIECE;
//					
//					mAnimationTime = 0.f;
//					mIsFlipping = false;
//				}
//			}
//		}
//		
//		
//		
//		public void setFlipping() {
//			mIsFlipping = true;
//		}
//	}
//
//}
